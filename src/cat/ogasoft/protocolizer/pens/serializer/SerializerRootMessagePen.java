package cat.ogasoft.protocolizer.pens.serializer;

import cat.ogasoft.protocolizer.pens.generation.Pen;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 25, 2017 [4:03:32 PM]
 *
 * @brief A class that contains all SerializerMessages of a protoc file.
 */
public class SerializerRootMessagePen extends Pen {

    private final String sJavaFQN;
    private final List<SerializerMessagePen> messages;
    private final Map<String, String> builders;

    private SerializerRootMessagePen(String sJavaClass, String sJavaFQN, Map<String, String> builders) {
        super(0, "public class " + sJavaClass + " {", "}");
        this.messages = new LinkedList<>();
        this.sJavaFQN = sJavaFQN;
        this.builders = builders;
    }

    public static SerializerRootMessagePen build(String sJavaClass, String sJavaFQN, Map<String, String> builders) {
        return new SerializerRootMessagePen(sJavaClass, sJavaFQN, builders);
    }

    public SerializerMessagePen newMessage(String sJavaClass, String mJavaFQN, String pJavaClass) {
        SerializerMessagePen message = SerializerMessagePen.build(sJavaFQN, sJavaClass, mJavaFQN, pJavaClass, builders);
        messages.add(message);
        return message;
    }

    public Map<String, String> getBuilders() {
        return builders;
    }

    public void constructMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        for (SerializerMessagePen smp : messages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (SerializerMessagePen mp : messages) {
            content.addAll(mp.content());
            content.add("\n");
        }
        super.addEnd(content);
        return content;
    }

    @Override
    public Iterator<String> iterator() {
        return content().iterator();
    }
}
