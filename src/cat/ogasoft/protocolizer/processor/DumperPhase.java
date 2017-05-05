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

import cat.ogasoft.protocolizer.exceptions.DumpperException;
import cat.ogasoft.protocolizer.pens.dumpers.DeserializerMessagePen;
import cat.ogasoft.protocolizer.pens.generation.EnumPen;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import cat.ogasoft.protocolizer.pens.dumpers.DumperFilePen;
import cat.ogasoft.protocolizer.pens.dumpers.SerializerMessagePen;
import cat.ogasoft.protocolizer.pens.dumpers.DumperRootMessagePen;
import cat.ogasoft.protocolizer.pens.dumpers.DumpersFilePen;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:08:11 PM]
 *
 * @brief Functional class for generate dumper files.
 */
public abstract class DumperPhase {

    /**
     * @pre methodsNS and enumsNS are complete
     * @post new DumperFilePen has been created
     * @param serialize if serialize must be wrotten.
     * @param deserialize if deserialize must be wroten.
     * @param sRoot root for dumper
     * @param sJavaPackage java package for dumper.
     * @param filePen for this dumper.
     * @param methodsNS translate function from java message FQN to protoc message FQN and vice-versa.
     * @param enumsNS translate function from java enumeration FQN to protoc enumeration FQN and vice-versa.
     * @return new DumperFilePen.
     * @throws DumpperException if any goes wrong.
     */
    public static DumpersFilePen dump(
            boolean serialize,
            boolean deserialize,
            String sRoot,
            String sJavaPackage,
            FilePen filePen,
            Map<String, String> methodsNS,
            Map<String, String> enumsNS) throws DumpperException {
        DumpersFilePen dumpers = new DumpersFilePen();
        try {
            if (serialize) {
                System.out.println("SERIALIZE");
                String sJavaClass = filePen.fileDescriptor.pJavaClass + "Serializer";
                File javaClassRoot = new File((sRoot + "." + sJavaPackage + ".serializers").replace('.', File.separatorChar));
                if (!javaClassRoot.exists() && !javaClassRoot.mkdirs()) {
                    throw new Exception("HO ho... can't create directory " + javaClassRoot.getAbsolutePath());
                }
                File fileDump = new File(javaClassRoot, sJavaClass + ".java");
                DumperFilePen serializedFilePen = DumperFilePen.build(fileDump, sJavaPackage + ".serializers", sJavaClass, filePen.fileDescriptor);
                DumperRootMessagePen rootMessages = serializedFilePen.buildRoot(methodsNS);
                //For each message inside message root.
                Iterator<MessagePen> messageIterador = filePen.messageIterator();
                while (messageIterador.hasNext()) {
                    MessagePen gMessagePen = messageIterador.next();
                    SerializerMessagePen sMessagePen = rootMessages.serializerMessage(gMessagePen.pJavaClass, gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.parallel);
                    sMessagePen.addMethod(gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.fieldIterator());
                    //For each message inside a message root.
                    serializeInnerMessage(gMessagePen, sMessagePen, enumsNS);
                }
                dumpers.setSerializer(serializedFilePen);
            }
            if (deserialize) {
                System.out.println("DESERIALIZE");
                String sJavaClass = filePen.fileDescriptor.pJavaClass + "Deserializer";
                File javaClassRoot = new File((sRoot + "." + sJavaPackage + ".deserializers").replace('.', File.separatorChar));
                if (!javaClassRoot.exists() && !javaClassRoot.mkdirs()) {
                    throw new Exception("HO ho... can't create directory " + javaClassRoot.getAbsolutePath());
                }
                File fileDump = new File(javaClassRoot, sJavaClass + ".java");
                DumperFilePen deserializedFilePen = DumperFilePen.build(fileDump, sJavaPackage + ".deserializers", sJavaClass, filePen.fileDescriptor);
                DumperRootMessagePen rootMessages = deserializedFilePen.buildRoot(methodsNS);
                //For each message inside message root.
                Iterator<MessagePen> messageIterador = filePen.messageIterator();
                while (messageIterador.hasNext()) {
                    MessagePen gMessagePen = messageIterador.next();
                    DeserializerMessagePen dMessagePen = rootMessages.deserializerMessage(gMessagePen.pJavaClass, gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.parallel);
                    dMessagePen.addMethod(gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.fieldIterator());
                    //For each message inside a message root.
                    deserializeInnerMessage(gMessagePen, dMessagePen, enumsNS);
                }
                dumpers.setDeserializer(deserializedFilePen);
            }
            Iterator<EnumPen> enumIterator = filePen.enumIterator();
            while (enumIterator.hasNext()) {
                EnumPen ep = enumIterator.next();
                enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
                enumsNS.put(ep.pJavaFQN, ep.mJavaFQN);
            }
        } catch (Exception e) {
            throw new DumpperException(e.getMessage());
        }
        return dumpers;
    }

    /**
     * @pre enumsNS is complete.
     * @post all mp inner messages for serialize has been created inside smp.
     * @param messagePen for new inner message.
     * @param smessagePen for new inner message.
     * @param enumsNS enumerations namespace.
     * @throws Exception if any goes wrong.
     */
    private static void serializeInnerMessage(MessagePen messagePen,
            SerializerMessagePen sMessagePen,
            Map<String, String> enumsNS) throws Exception {
        Iterator<MessagePen> messagesIterator = messagePen.messageIterator();
        while (messagesIterator.hasNext()) {
            //Each inner message is translated to public static method.
            MessagePen inMessage = messagesIterator.next();
            SerializerMessagePen smp2 = sMessagePen.buildInn(inMessage.pJavaClass, inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.parallel);
            smp2.addMethod(inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.pJavaFQN, inMessage.fieldIterator());
            serializeInnerMessage(inMessage, smp2, enumsNS);
        }
        Iterator<EnumPen> enumIterator = messagePen.enumIterator();
        while (enumIterator.hasNext()) {
            EnumPen ep = enumIterator.next();
            enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
        }
    }

    /**
     * @pre enumsNS is complete.
     * @post all mp inner messages for deserialize has been created inside smp.
     * @param messagePen for new inner message.
     * @param smessagePen for new inner message.
     * @param enumsNS enumerations namespace.
     * @throws Exception if any goes wrong.
     */
    private static void deserializeInnerMessage(MessagePen mp,
            DeserializerMessagePen smp,
            Map<String, String> enumsNS) throws Exception {
        Iterator<MessagePen> messagesIterator = mp.messageIterator();
        while (messagesIterator.hasNext()) {
            //Each inner message is translated to public static method.
            MessagePen inMessage = messagesIterator.next();
            DeserializerMessagePen smp2 = smp.buildInn(inMessage.pJavaClass, inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.parallel);
            smp2.addMethod(inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.pJavaFQN, inMessage.fieldIterator());
            deserializeInnerMessage(inMessage, smp2, enumsNS);
        }
        Iterator<EnumPen> enumIterator = mp.enumIterator();
        while (enumIterator.hasNext()) {
            EnumPen ep = enumIterator.next();
            enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
        }
    }
}
