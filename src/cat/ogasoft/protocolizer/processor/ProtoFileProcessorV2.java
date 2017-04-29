package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.exceptions.SerializationException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.serializer.SerializerFilePen;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;

/**
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:20:14 PM]
 *
 * @brief @{
 *      Processor for: 
 *          1-Generate protoc files.
 *          2-Compile protoc files to Java classes.
 *          3-Generate serializer classe to parse Java classes to protoc messages.
 * @}
 */
@SupportedAnnotationTypes({"cat.ogasoft.protocolizer.annotations.ProtoFileV2.File",
    "cat.ogasoft.protocolizer.annotations.ProtoFileV2.Compiler",
    "cat.ogasoft.protocolizer.annotations.ProtoFileV2.Serializer"})
public class ProtoFileProcessorV2 extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean processed = true;
        try {
            Map<String, String> mFQN2pFQN = new HashMap<>(); //<Map from java message FQN to protoc FQN needed for nested messages
            Map<String, FilePen> filePensMap = new HashMap<>();//<A map with java message FQN to FilePen.

            //PHASE 1- Generate protoc messages.
            /**
             * For each element annotated with File, we must to generate a
             * protoc message.
             */
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.File.class)) {
                ProtoFileV2.File protoc = element.getAnnotation(ProtoFileV2.File.class);
                //We only generate protoc messages for theses Files with generateSource at ture.
                if (protoc.generateSource()) {
                    FilePen fp = GenerationPhase.processFile(element, protoc, mFQN2pFQN);
                    filePensMap.put(element.asType().toString(), fp);
                }
            }

            //PHASE 2- Compile protoc messages.
            //At this point all protoc messages has been generated, so must we compile theses protoc files?
            CompilerPhase.processCompiler(roundEnv);

            //PHASE 3- Generate Serializer classes.
            /**@{*
                  <Map pJavaFQN to FQN method that can build a protoc message through it java class
                  This methods acts as namespace for the messages.
             **@}*/
            final Map<String, String> methodsNS = new HashMap<>();
            final Map<String, String> enumsNS = new HashMap<>();

            /**
             * We have to construct all the methods at the end of the process,
             * so first of all we collect all methods in methods list.
             */
            final List<SerializerFilePen> serializerFilePens = new LinkedList<>();
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.Serialize.class)) {
                FilePen fp = filePensMap.get(element.asType().toString());
                //We need the FilePen associated with the message to generate the serializer.
                if (fp == null) {
                    throw new SerializationException("You can't generate a serialization for class that is not a message");
                }
                ProtoFileV2.Serialize serialize = element.getAnnotation(ProtoFileV2.Serialize.class);
                serializerFilePens.add(SerializerPhase.serialize(serialize.root(), serialize.javaPackage(), fp, methodsNS, enumsNS));
            }
            for (SerializerFilePen serializerFilePen : serializerFilePens) {
                serializerFilePen.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
                serializerFilePen.dump();
            }
        } catch (Throwable t) {
            processed = false;
        }
        return processed;
    }
}
