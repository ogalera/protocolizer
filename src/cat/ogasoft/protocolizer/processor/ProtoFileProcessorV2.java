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
import static cat.ogasoft.protocolizer.annotations.ProtoFileV2.Dumpper.DumpperTypes.BOTH;
import static cat.ogasoft.protocolizer.annotations.ProtoFileV2.Dumpper.DumpperTypes.DESERIALIZER;
import static cat.ogasoft.protocolizer.annotations.ProtoFileV2.Dumpper.DumpperTypes.SERIALIZER;
import cat.ogasoft.protocolizer.exceptions.CompilerException;
import cat.ogasoft.protocolizer.exceptions.DumperException;
import cat.ogasoft.protocolizer.exceptions.GenerationException;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.TypeElement;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.dumpers.DumpersFilePen;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.lang.model.element.Element;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    private static final String DUMPERS_PATH = "cat.ogasoft.protocolizer.dumpers"; //<Path for dumpers.
    private static final Logger LOG = LogManager.getLogger();

    /**
     * @pre protocolizer elements has been annotated correctly.
     * @post protocolizer has been executed.
     * @param annotations
     * @param roundEnv all elements.
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        LOG.info("Protocolizer Initiated");
        boolean processed = true;
        try {
            Map<String, String> mFQN2pFQN = new HashMap<>(); //<Map from java message FQN to protoc FQN needed for nested messages
            Map<String, FilePen> filePensMap = new HashMap<>();//<A map with java message FQN to FilePen.

            //PHASE 1- Generate protoc messages.
            /**
             * For each element annotated with File, we must to generate a
             * protoc message.
             */
            LOG.info("***GENERATION PHASE");
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.File.class)) {
                ProtoFileV2.File protoc = element.getAnnotation(ProtoFileV2.File.class);
                //We only generate protoc messages for theses Files with generateSource at ture.
                if (protoc.generateSource()) {
                    FilePen fp = GenerationPhase.processFile(element, protoc, mFQN2pFQN);
                    filePensMap.put(element.asType().toString(), fp);
                }
            }

            for (FilePen filePen : filePensMap.values()) {
                filePen.dump(filePensMap);
            }

            LOG.info("\t" + filePensMap.values().size() + " protoc files written");

            //PHASE 2- Compile protoc messages.
            //At this point all protoc messages has been generated, so must we compile theses protoc files?
            LOG.info("***COMPILER PHASE");
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
            LOG.info("***DUMPER PHASE");
            final List<DumpersFilePen> dummperFilePens = new LinkedList<>();
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.Dumpper.class)) {
                FilePen fp = filePensMap.get(element.asType().toString());
                //We need the FilePen associated with the message to generate the serializer.
                if (fp == null) {
                    throw new DumperException("You can't generate a dumper for class that is not a message");
                }
                ProtoFileV2.Dumpper dumper = element.getAnnotation(ProtoFileV2.Dumpper.class);
                boolean serialize = dumper.type() == SERIALIZER || dumper.type() == BOTH;
                boolean deserialize = dumper.type() == DESERIALIZER || dumper.type() == BOTH;
                dummperFilePens.add(DumperPhase.dump(serialize, deserialize, dumper.root(), DUMPERS_PATH, fp, methodsNS, enumsNS));
            }

            for (DumpersFilePen dumperFilePen : dummperFilePens) {
                if (dumperFilePen.hasSerializer()) {
                    dumperFilePen.getSerializer().constructSerializerMethods(methodsNS, enumsNS, mFQN2pFQN);
                    dumperFilePen.getSerializer().dumpSerialize();
                }
                if (dumperFilePen.hasDeserializer()) {
                    dumperFilePen.getDeserializer().constructDeserializerMethods(methodsNS, enumsNS, mFQN2pFQN);
                    dumperFilePen.getDeserializer().dumpDeserialize();
                }
            }
            LOG.info(dummperFilePens.size() + " dumpers writen");
            LOG.info("ALL OK!!!");
        } catch (GenerationException ge) {
            LOG.info("FAILED!!!, Generation phase ERROR, message: " + ge.getMessage());
            processed = false;
        } catch (CompilerException ce) {
            LOG.info("FAILED!!!, Compiler phase ERROR, message: " + ce.getMessage());
            processed = false;
        } catch (DumperException de) {
            LOG.info("FAILED!!!, Dumper phase ERROR, message: " + de.getMessage());
            processed = false;
        } catch (IOException ioe) {
            LOG.info("FAILED!!!, IO error, message: " + ioe.getMessage());
            processed = false;
        }
        return processed;
    }
}
