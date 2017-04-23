package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.exceptions.ProcessException;
import java.io.File;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.pens.EnumPen;
import cat.ogasoft.protocolizer.pens.FilePen;
import cat.ogasoft.protocolizer.pens.MessagePen;
import cat.ogasoft.protocolizer.pens.MessagePen.Field;
import cat.ogasoft.protocolizer.pens.serializer.SerializerFilePen;
import cat.ogasoft.protocolizer.pens.serializer.SerializerMessagePen;
import com.google.common.base.Strings;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Iterator;
import javax.lang.model.element.ElementKind;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:20:14 PM]
 *
 * @brief Processor that generates all the files that composes the protocol.
 */
@SupportedAnnotationTypes({"cat.ogasoft.protocolizer.annotations.ProtoFileV2.File",
    "cat.ogasoft.protocolizer.annotations.ProtoFileV2.Compiler",
    "cat.ogasoft.protocolizer.annotations.ProtoFileV2.Serializer"})
public class ProtoFileProcessorV2 extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean processed = true;
        try {
            processFile(roundEnv);
            processCompiler(roundEnv);
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            processed = false;
        }
        return processed;
    }

    private void processFile(RoundEnvironment roundEnv) throws Exception {
        for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.File.class)) {
            ProtoFileV2.File protoc = element.getAnnotation(ProtoFileV2.File.class);
            ProtoFileV2.Serialize serialize = element.getAnnotation(ProtoFileV2.Serialize.class);
            if (protoc.generateSource() || serialize != null) {
                File protocPath = new File(protoc.protoFilePaths().replace('.', File.separatorChar));
                if (!protocPath.exists() && !protocPath.mkdirs()) {
                    throw new ProcessException("Unable to generate destination directory for protoc [" + protocPath.getAbsolutePath() + "]");
                }
                //First of all, we need to generate a protocol buffer field (the message).
                FilePen filePen = generateProtoc(element);
                if (protoc.generateSource()) {
                    filePen.dump(new File(protocPath, protoc.name() + ".proto"));
                }
                if (serialize != null) {
                    serialize(serialize.root(), serialize.javaPackage(), nullOrEmptyToName(protoc.name(), element), filePen);
                }
            }
        }
    }

    private void serialize(String root, String packagee, String name, FilePen filePen) throws IOException, ProcessException {
        name += "Serialized";
        File dsp = new File(normalizePath(root + "." + packagee));
        if (!dsp.exists() && !dsp.mkdirs()) {
            throw new ProcessException("HO ho... can't create directory " + dsp.getAbsolutePath());
        }
        File target = new File(dsp, name + ".java");
        System.out.println("Create " + target.getAbsolutePath());
        Iterator<MessagePen> messageIterador = filePen.messageIterator();
        SerializerFilePen sp = SerializerFilePen.build(packagee);
        SerializerMessagePen smp = sp.buildPublicMessage(name);
        while (messageIterador.hasNext()) {
            MessagePen mp = messageIterador.next();
            Iterator<Field> fieldsIterator = mp.fieldIterator();
            SerializerMessagePen message = smp.messagePen(mp.getName());
            while (fieldsIterator.hasNext()) {
                Field field = fieldsIterator.next();
                
            }
        }
        System.out.println("done");
        sp.dump(target);
    }

    private void processCompiler(RoundEnvironment roundEnv) throws Exception {
        for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.Compiler.class)) {
            ProtoFileV2.Compiler compiler = element.getAnnotation(ProtoFileV2.Compiler.class);
            if (compiler.compile()) {
                File dst = new File(normalizePath(compiler.protoFilePaths()));
                if (!dst.exists() && !dst.mkdirs()) {
                    throw new ProcessException("Cann not be created directory " + dst.getAbsolutePath());
                }
                String base = normalizePath(compiler.protoFilePaths());
                File rootDirectori = new File(base);
                //Compile all the files in compiler.protoFilePaths()
                FileFilter filter = new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getName().toLowerCase().endsWith(".proto");
                    }
                };
                DefaultExecutor de = new DefaultExecutor();
                for (File protoc : rootDirectori.listFiles(filter)) {
                    System.out.println("compiling " + protoc.getAbsolutePath());
                    CommandLine cmd = CommandLine.parse(compiler.command() + " --proto_path=src " + compiler.language().option + "=src " + base + File.separatorChar + protoc.getName());
                    int result = de.execute(cmd);
                    if (result != 0) {
                        throw new ProcessException("HO ho... somthing went wrong, code: " + result);
                    }
                }
            }
        }
    }

    private String normalizePath(String path) {
        return path.replace('.', File.separatorChar);
    }

    /**
     * @pre --
     * @post Generates the data that representas a protocol buffer field that is
     * represented inside element.
     */
    private FilePen generateProtoc(Element element) throws Exception {
        ProtoFileV2.File file = element.getAnnotation(ProtoFileV2.File.class);
        FilePen filePen = FilePen.build().setJavaPackage(file.javaPackage()).setOutterClassName(nullOrEmptyToName(file.name(), element));
        imports(filePen, element.getAnnotationsByType(ProtoFileV2.File.Import.class));
        options(filePen, element.getAnnotationsByType(ProtoFileV2.File.Option.class));
        content(filePen, element);
        return filePen;
    }

    /**
     * @pre --
     * @post Returns all messages inside element.
     */
    private void content(FilePen filePen, Element element) throws Exception {
        for (Element innerElement : element.getEnclosedElements()) {
            ProtoFileV2.File.Message pfMessage = innerElement.getAnnotation(ProtoFileV2.File.Message.class);
            if (pfMessage != null) {
                message(filePen.messagePen(nullOrEmptyToName(pfMessage.name(), innerElement)), innerElement);
            } else {
                ProtoFileV2.File.Enum pfEnum = innerElement.getAnnotation(ProtoFileV2.File.Enum.class);
                if (pfEnum != null) {
                    EnumPen ep = filePen.enumPen(nullOrEmptyToName(pfEnum.name(), innerElement));
                    getEnum(ep, innerElement);
                }
            }
        }
    }

    /**
     * @pre --
     * @post Returns the message inside innerElement.
     */
    private void message(MessagePen mp, Element innerElement) throws Exception {
        for (Element innerInnerElement : innerElement.getEnclosedElements()) {
            ProtoFileV2.File.Message.Field pfField = innerInnerElement.getAnnotation(ProtoFileV2.File.Message.Field.class);
            if (pfField != null) {
                String finalType;
                ProtoFileV2.File.Message.Field.DataType typus = pfField.type();
                String type = innerInnerElement.asType().toString();
                type = type.substring(type.lastIndexOf(".") + 1);
                if (pfField.type() == ProtoFileV2.File.Message.Field.DataType.CALCULATED) {
                    typus = ProtoFileV2.File.Message.Field.DataType.calculate(type);
                }
                typus.validate(type);
                if (typus == ProtoFileV2.File.Message.Field.DataType.COMPOSED) {
                    finalType = type;
                } else {
                    finalType = typus.name().toLowerCase();
                }
                //Field found.
                Field field = new Field(pfField.comment(), pfField.label().name().toLowerCase(), nullOrEmptyToName(pfField.name(), innerInnerElement), finalType);
                mp.addField(field);
            } else {
                ProtoFileV2.File.Enum pfEnum = innerInnerElement.getAnnotation(ProtoFileV2.File.Enum.class);
                if (pfEnum != null) {
                    //Enum found.
                    EnumPen ep = mp.enumPen(nullOrEmptyToName(pfEnum.name(), innerInnerElement));
                    getEnum(ep, innerInnerElement);
                } else {
                    //Inner message found.
                    ProtoFileV2.File.Message pf = innerInnerElement.getAnnotation(ProtoFileV2.File.Message.class);
                    if (pf != null) {
                        message(mp.messagePen(nullOrEmptyToName(pf.name(), innerInnerElement)), innerInnerElement);
                    }
                }
            }
        }
    }

    private String nullOrEmptyToName(String name, Element element) {
        return Strings.isNullOrEmpty(name) ? element.getSimpleName().toString() : name;
    }

    private void getEnum(EnumPen ep, Element element) throws Exception {
        if (element.getKind() != ElementKind.ENUM) {
            throw new Exception(element.getSimpleName() + " is not an ennumeration");
        }
        for (Element inner : element.getEnclosedElements()) {
            if (inner.getKind() == ElementKind.ENUM_CONSTANT) {
                ep.addValue(inner.getSimpleName().toString());
            }
        }
    }

    private void imports(FilePen filePen, ProtoFileV2.File.Import[] imports) {
        for (ProtoFileV2.File.Import importt : imports) {
            filePen.addImport(importt.access().name().toLowerCase(), importt.name());
        }
    }

    private void options(FilePen filePen, ProtoFileV2.File.Option[] options) {
        for (ProtoFileV2.File.Option option : options) {
            filePen.addOption(option.name(), option.value());
        }
    }
}
