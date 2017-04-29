/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.pens.serializer;

import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import cat.ogasoft.protocolizer.pens.generation.Pen;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 23, 2017 [10:07:54 PM]
 *
 * @brief DESCRIPTION
 */
public class SerializerMessagePen extends Pen {

    private final List<SerializerMessagePen> messages;
    private final Map<String, String> builders;
    private final String sJavaFQN;
    private final String mJavaFQN;
    private final List<SerializerMethodPen> methods;

    private SerializerMessagePen(int level, String sJavaClass, String sJavaFQN, String mJavaFQN, String pJavaClass, Map<String, String> builders) {
        //For each protoc message, we have a public static class...
        super(level, "public static class " + sJavaClass + " {", "}");
        super.writeInnTabln("public byte[] dump(" + mJavaFQN + " target){");
        super.writeInnInnTabln("return build" + pJavaClass + "(target).toByteArray();");
        super.writeInnTabln("}");
        this.methods = new LinkedList<>();
        this.messages = new LinkedList<>();
        this.builders = builders;
        this.sJavaFQN = sJavaFQN;
        this.mJavaFQN = mJavaFQN;
    }

    public static SerializerMessagePen build(String sJavaPackage, String sJavaClass, String mJavaFQN, String pJavaClass, Map<String, String> builders) {
        String sJavaFQN = sJavaPackage + '.' + sJavaClass;
        SerializerMessagePen pen = new SerializerMessagePen(1, sJavaClass, sJavaFQN, mJavaFQN, pJavaClass, builders);
        return pen;
    }

    public SerializerMessagePen buildInn(String sJavaClass, String mJavaFQN, String pJavaClass) {
        String sJavaFQNtmp = this.sJavaFQN + '.' + sJavaClass;
        SerializerMessagePen pen = new SerializerMessagePen(level + 1, sJavaClass, sJavaFQNtmp, mJavaFQN, pJavaClass, builders);
        this.messages.add(pen);
        return pen;
    }

    public SerializerMethodPen addMethod(String mJavaFQN, String pJavaClass, String pJavaFQN, Iterator<MessagePen.Field> fields) throws Exception {
        if (builders.containsKey(pJavaFQN)) {
            throw new Exception("Ja existeix un metode que retorna " + pJavaFQN);
        }
        String methodName = "build" + pJavaClass;
        SerializerMethodPen method = SerializerMethodPen.build(level + 1, methodName, mJavaFQN, pJavaFQN, fields);
        methods.add(method);
        builders.put(pJavaFQN, this.sJavaFQN + '.' + methodName);
        return method;
    }

    public void constructMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        for (SerializerMessagePen smp : messages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
        for (SerializerMethodPen smp : methods) {
            smp.construct(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (SerializerMessagePen smp : messages) {
            content.addAll(smp.content());
        }
        for (SerializerMethodPen mp : methods) {
            content.addAll(mp.content());
        }
        super.addEnd(content);
        return content;
    }

    @Override
    public Iterator<String> iterator() {
        return content().iterator();
    }

}
