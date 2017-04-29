package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.exceptions.SerializationException;
import cat.ogasoft.protocolizer.pens.generation.EnumPen;
import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import cat.ogasoft.protocolizer.pens.serializer.SerializerFilePen;
import cat.ogasoft.protocolizer.pens.serializer.SerializerMessagePen;
import cat.ogasoft.protocolizer.pens.serializer.SerializerRootMessagePen;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:08:11 PM]
 *
 * @brief DESCRIPTION
 */
public class SerializerPhase {

    public static SerializerFilePen serialize(String sRoot,
            String sJavaPackage,
            FilePen filePen,
            Map<String, String> methodsNS,
            Map<String, String> enumsNS) throws SerializationException {
        SerializerFilePen serializedFilePen = null;
        try {
            String sJavaClass = filePen.fileDescriptor.pJavaClass + "Serialized";
            File javaClassRoot = new File((sRoot + "." + sJavaPackage).replace('.', File.separatorChar));
            if (!javaClassRoot.exists() && !javaClassRoot.mkdirs()) {
                throw new Exception("HO ho... can't create directory " + javaClassRoot.getAbsolutePath());
            }
            File fileDump = new File(javaClassRoot, sJavaClass + ".java");
            serializedFilePen = SerializerFilePen.build(fileDump, sJavaPackage, sJavaClass, filePen.fileDescriptor);
            SerializerRootMessagePen rootMessages = serializedFilePen.buildRoot(methodsNS);
            //For each message inside message root.
            Iterator<MessagePen> messageIterador = filePen.messageIterator();
            while (messageIterador.hasNext()) {
                MessagePen gMessagePen = messageIterador.next();
                SerializerMessagePen sMessagePen = rootMessages.newMessage(gMessagePen.pJavaClass, gMessagePen.mJavaFQN, gMessagePen.pJavaClass);
                sMessagePen.addMethod(gMessagePen.mJavaFQN, gMessagePen.pJavaClass, gMessagePen.pJavaFQN, gMessagePen.fieldIterator());
                //For each message inside a message root.
                serializeMessage(gMessagePen, sMessagePen, enumsNS);
            }
            Iterator<EnumPen> enumIterator = filePen.enumIterator();
            while (enumIterator.hasNext()) {
                EnumPen ep = enumIterator.next();
                enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
            }
        } catch (Exception e) {
            throw new SerializationException(e.getMessage());
        }
        return serializedFilePen;
    }

    private static void serializeMessage(MessagePen mp, SerializerMessagePen smp, Map<String, String> enumsNS) throws Exception {
        Iterator<MessagePen> messagesIterator = mp.messageIterator();
        while (messagesIterator.hasNext()) {
            //Each inner message is translated to public static method.
            MessagePen inMessage = messagesIterator.next();
            SerializerMessagePen smp2 = smp.buildInn(inMessage.pJavaClass, inMessage.mJavaFQN, inMessage.pJavaClass);
            smp2.addMethod(inMessage.mJavaFQN, inMessage.pJavaClass, inMessage.pJavaFQN, inMessage.fieldIterator());
            serializeMessage(inMessage, smp2, enumsNS);
        }
        Iterator<EnumPen> enumIterator = mp.enumIterator();
        while (enumIterator.hasNext()) {
            EnumPen ep = enumIterator.next();
            enumsNS.put(ep.mJavaFQN, ep.pJavaFQN);
        }
    }
}
