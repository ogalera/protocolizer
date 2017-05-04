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
import cat.ogasoft.protocolizer.pens.dumppers.DeserializerMessagePen;
import cat.ogasoft.protocolizer.pens.generation.EnumPen;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import cat.ogasoft.protocolizer.pens.dumppers.DumpperFilePen;
import cat.ogasoft.protocolizer.pens.dumppers.SerializerMessagePen;
import cat.ogasoft.protocolizer.pens.dumppers.DumpperRootMessagePen;
import cat.ogasoft.protocolizer.pens.dumppers.DumppersFilePen;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:08:11 PM]
 *
 * @brief DESCRIPTION
 */
public class DumpperPhase {

    public static DumppersFilePen dump(
            boolean serialize,
            boolean deserialize,
            String sRoot,
            String sJavaPackage,
            FilePen filePen,
            Map<String, String> methodsNS,
            Map<String, String> enumsNS) throws DumpperException {
        DumppersFilePen dumppers = new DumppersFilePen();
        try {
            if (serialize) {
                System.out.println("SERIALIZE");
                String sJavaClass = filePen.fileDescriptor.pJavaClass + "Serializer";
                File javaClassRoot = new File((sRoot + "." + sJavaPackage + ".serializers").replace('.', File.separatorChar));
                if (!javaClassRoot.exists() && !javaClassRoot.mkdirs()) {
                    throw new Exception("HO ho... can't create directory " + javaClassRoot.getAbsolutePath());
                }
                File fileDump = new File(javaClassRoot, sJavaClass + ".java");
                DumpperFilePen serializedFilePen = DumpperFilePen.build(fileDump, sJavaPackage + ".serializers", sJavaClass, filePen.fileDescriptor);
                DumpperRootMessagePen rootMessages = serializedFilePen.buildRoot(methodsNS);
                //For each message inside message root.
                Iterator<MessagePen> messageIterador = filePen.messageIterator();
                while (messageIterador.hasNext()) {
                    MessagePen gMessagePen = messageIterador.next();
                    SerializerMessagePen sMessagePen = rootMessages.serializerMessage(gMessagePen.pJavaClass, gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.parallel);
                    sMessagePen.addMethod(gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.fieldIterator());
                    //For each message inside a message root.
                    serializeInnerMessage(gMessagePen, sMessagePen, enumsNS);
                }
                dumppers.setSerializer(serializedFilePen);
            }
            if (deserialize) {
                System.out.println("DESERIALIZE");
                String sJavaClass = filePen.fileDescriptor.pJavaClass + "Deserializer";
                File javaClassRoot = new File((sRoot + "." + sJavaPackage + ".deserializers").replace('.', File.separatorChar));
                if (!javaClassRoot.exists() && !javaClassRoot.mkdirs()) {
                    throw new Exception("HO ho... can't create directory " + javaClassRoot.getAbsolutePath());
                }
                File fileDump = new File(javaClassRoot, sJavaClass + ".java");
                DumpperFilePen deserializedFilePen = DumpperFilePen.build(fileDump, sJavaPackage + ".deserializers", sJavaClass, filePen.fileDescriptor);
                DumpperRootMessagePen rootMessages = deserializedFilePen.buildRoot(methodsNS);
                //For each message inside message root.
                Iterator<MessagePen> messageIterador = filePen.messageIterator();
                while (messageIterador.hasNext()) {
                    MessagePen gMessagePen = messageIterador.next();
                    DeserializerMessagePen dMessagePen = rootMessages.deserializerMessage(gMessagePen.pJavaClass, gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.parallel);
                    dMessagePen.addMethod(gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.fieldIterator());
                    //For each message inside a message root.
                    deserializeInnerMessage(gMessagePen, dMessagePen, enumsNS);
                }
                dumppers.setDeserializer(deserializedFilePen);
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
        return dumppers;
    }

    private static void serializeInnerMessage(MessagePen mp, SerializerMessagePen smp, Map<String, String> enumsNS) throws Exception {
        Iterator<MessagePen> messagesIterator = mp.messageIterator();
        while (messagesIterator.hasNext()) {
            //Each inner message is translated to public static method.
            MessagePen inMessage = messagesIterator.next();
            SerializerMessagePen smp2 = smp.buildInn(inMessage.pJavaClass, inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.parallel);
            smp2.addMethod(inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.pJavaFQN, inMessage.fieldIterator());
            serializeInnerMessage(inMessage, smp2, enumsNS);
        }
        Iterator<EnumPen> enumIterator = mp.enumIterator();
        while (enumIterator.hasNext()) {
            EnumPen ep = enumIterator.next();
            enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
        }
    }

    private static void deserializeInnerMessage(MessagePen mp, DeserializerMessagePen smp, Map<String, String> enumsNS) throws Exception {
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
