/*
 * Copyright 2017 Oscar Galera i Alfaro.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.exceptions.GenerationException;
import cat.ogasoft.protocolizer.pens.generation.EnumPen;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import com.google.common.base.Strings;
import java.io.File;
import java.util.Map;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.MirroredTypeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:03:57 PM]
 *
 * @brief Functional class for generate protoc files.
 */
public abstract class GenerationPhase {

    private static final Logger LOG = LogManager.getLogger();

    /**
     * @pre element is annotated with File.
     * @post protoc file based on element has been created.
     * @throws GenerationException if any goes wrong.
     */
    public static FilePen processFile(Element element,
            ProtoFileV2.File protoc,
            Map<String, String> mFQN2pFQN) throws GenerationException {
        FilePen filePen = null;
        try {
            String pJavaName = nullOrEmptyToName(protoc.pJavaName(), element);
            char separator = File.separatorChar;
            String protocPath = "src" + separator + "cat" + separator + "ogasoft" + separator + "protocolizer" + separator + "protoc";
            String protocFile = protocPath + File.separatorChar + pJavaName;
            LOG.info("\tWriting " + protocFile + "...");
            File fProtocPath = new File(protocPath);
            if (!fProtocPath.exists() && !fProtocPath.mkdirs()) {
                throw new Exception("Unable to generate destination directory for protoc [" + fProtocPath.getAbsolutePath() + "]");
            }
            filePen = generateProtoc(protocFile, element, mFQN2pFQN);
            LOG.info("\t" + protocFile + " written");
        } catch (Exception e) {
            throw new GenerationException(e.getMessage());
        }
        return filePen;
    }

    /**
     * @pre --
     * @post Returns a FilePen that represents a protocFile equivalent to
     * message parameter.
     */
    private static FilePen generateProtoc(String protocFile, Element message, Map<String, String> mFQN2pFQN) throws Exception {
        ProtoFileV2.File anFile = message.getAnnotation(ProtoFileV2.File.class);
        String mJavaClass = message.getSimpleName().toString(); //<Name of the java data model.
        String mJavaPackage = message.asType().toString(); //<Fully qualified name of the java data model.
        mJavaPackage = mJavaPackage.substring(0, mJavaPackage.lastIndexOf('.')); //<Package of the java data model
        String pJavaClass = nullOrEmptyToName(anFile.pJavaName(), message); //<Java class protoc.
        String pJavaPackage = anFile.pJavaPackage(); //<Package of Java class protoc.
        FilePen filePen = FilePen.build(protocFile, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass);
        imports(filePen, message.getAnnotationsByType(ProtoFileV2.File.Import.class));
        options(filePen, message.getAnnotationsByType(ProtoFileV2.File.Option.class));
        content(filePen, message, mFQN2pFQN);
        return filePen;
    }

    /**
     * @pre --
     * @post Returns all messages inside element.
     */
    private static void content(FilePen filePen, Element element, Map<String, String> mFQN2pFQN) throws Exception {
        for (Element innerElement : element.getEnclosedElements()) {
            ProtoFileV2.File.Message pfMessage = innerElement.getAnnotation(ProtoFileV2.File.Message.class);
            if (pfMessage != null) {
                String mJavaClass = innerElement.getSimpleName().toString();
                String pJavaClass = nullOrEmptyToName(pfMessage.name(), innerElement);
                MessagePen mp = filePen.messagePen(mJavaClass, pJavaClass, pfMessage.parallel());
                mFQN2pFQN.put(mp.mJavaFQN, mp.pJavaFQN);
                message(mp, innerElement, mFQN2pFQN);
            } else {
                ProtoFileV2.File.Enum pfEnum = innerElement.getAnnotation(ProtoFileV2.File.Enum.class);
                if (pfEnum != null) {
                    String mJavaClass = innerElement.getSimpleName().toString();
                    String pJavaClass = nullOrEmptyToName(pfEnum.name(), innerElement);
                    getEnum(filePen.enumPen(mJavaClass, pJavaClass), innerElement);
                }
            }
        }
    }

    /**
     * @pre --
     * @post Returns the message inside innerElement.
     */
    private static void message(MessagePen mp, Element element, Map<String, String> mFQN2pFQN) throws Exception {
        for (Element innerElement : element.getEnclosedElements()) {
            ProtoFileV2.File.Message.Field pfField = innerElement.getAnnotation(ProtoFileV2.File.Message.Field.class);
            if (pfField != null) {
                ProtoFileV2.File.Message.Field.DataType typus = pfField.type();
                String javaTypeFQN = innerElement.asType().toString();
                String javaType = javaTypeFQN.substring(javaTypeFQN.lastIndexOf(".") + 1).replace("<", "").replace(">", "");
                String composedType = null;
                //No s'ha especificat el tipus, cal calcular-lo...
                if (pfField.type() == ProtoFileV2.File.Message.Field.DataType.CALCULATED) {
                    typus = ProtoFileV2.File.Message.Field.DataType.calculate(javaType);
                } else {
                    typus.validate(javaType);
                }
                if (typus == ProtoFileV2.File.Message.Field.DataType.COMPOSED) {
                    composedType = javaType;
                }
                //Field found.
                mp.addField(pfField.comment(),
                        pfField.label(),
                        innerElement.getSimpleName().toString(),
                        innerElement.asType().toString(),
                        nullOrEmptyToName(pfField.name(), innerElement),
                        typus,
                        composedType);
            } else {
                ProtoFileV2.File.Enum pfEnum = innerElement.getAnnotation(ProtoFileV2.File.Enum.class);
                if (pfEnum != null) {
                    //Enum found.
                    String mJavaClass = innerElement.getSimpleName().toString();
                    String pJavaClass = nullOrEmptyToName(pfEnum.name(), innerElement);
                    EnumPen ep = mp.enumPen(mJavaClass, pJavaClass);
                    getEnum(ep, innerElement);
                } else {
                    //Inner message found.
                    ProtoFileV2.File.Message pf = innerElement.getAnnotation(ProtoFileV2.File.Message.class);
                    if (pf != null) {
                        String mJavaClass = innerElement.getSimpleName().toString();
                        String pJavaClass = nullOrEmptyToName(pf.name(), innerElement);
                        MessagePen messagePen = mp.messagePen(mJavaClass, pJavaClass, pf.parallel());
                        mFQN2pFQN.put(messagePen.mJavaFQN, messagePen.pJavaFQN);
                        message(messagePen, innerElement, mFQN2pFQN);
                    }
                }
            }
        }
    }

    /**
     * @pre --
     * @post all imports has been added to filePen.
     */
    private static void imports(FilePen filePen, ProtoFileV2.File.Import[] imports) throws GenerationException {
        for (ProtoFileV2.File.Import importt : imports) {
            try {
                importt.importClass().getName();
            } catch (MirroredTypeException mte) {
                filePen.addImport(importt.access().getName(), mte.getTypeMirror().toString());
            }
        }
    }

    /**
     * @pre --
     * @post all options has been added to filePen.
     */
    private static void options(FilePen filePen, ProtoFileV2.File.Option[] options) {
        for (ProtoFileV2.File.Option option : options) {
            filePen.addOption(option.name(), option.value());
        }
    }

    /**
     * @pre --
     * @post all enums has been added to enumPen.
     */
    private static void getEnum(EnumPen enumPen, Element element) throws Exception {
        if (element.getKind() != ElementKind.ENUM) {
            throw new Exception(element.getSimpleName() + " is not an ennumeration");
        }
        for (Element inner : element.getEnclosedElements()) {
            if (inner.getKind() == ElementKind.ENUM_CONSTANT) {
                enumPen.addValue(inner.getSimpleName().toString());
            }
        }
    }

    /**
     * @pre --
     * @post returns name or element simple name if name is empty or null
     */
    private static String nullOrEmptyToName(String name, Element element) {
        return Strings.isNullOrEmpty(name) ? element.getSimpleName().toString() : name;
    }
}
