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
package cat.ogasoft.protocolizer.pens.dumpers;

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
 * @brief A pen to write serialize protoc messages.
 */
public class SerializerMessagePen extends Pen {

    private final List<SerializerMessagePen> messages;//<Nested messages.
    private final Map<String, String> builders;//<Translate function from protoc FQN to serialize methods.
    private final String sJavaFQN;//<serialize Java FQN
    private final List<SerializerMethodPen> methods;//<Nested methods to serialize nested messages.

    /**
     * @pre level >= 0
     * @post New serialize message pen has been created.
     * @param level for DMP.
     * @param dJavaClass for DMP.
     * @param dJavaFQN for  DMP.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @param pJavaFQN for DMP.
     * @param builders map from mJavaFQN to his deserialize method FQN.
     * @param parallel if the serialization must be done in parallel.
     */
    private SerializerMessagePen(int level,
            String sJavaClass,
            String sJavaFQN,
            String mJavaFQN,
            String pJavaClass,
            Map<String, String> builders,
            boolean parallel) {
        //For each protoc message, we have a public static class...
        super(level, "public static class " + sJavaClass + (parallel ? " extends Thread implements cat.ogasoft.protocolizer.SerializerProtoWorker<" + mJavaFQN + ">" : "") + " {", "}");
        if (parallel) {
            super.writeInnln("private byte[] bytes;");
            super.writeInnln("private " + mJavaFQN + " container;");
            super.newLine();
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public void work(" + mJavaFQN + " container) {");
            super.writeInnInnTabln("this.container = container;");
            super.writeInnInnTabln("super.setName(\"" + sJavaFQN + "Worker\");");
            super.writeInnInnTabln("start();");
            super.writeInnln("}");
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public byte[] waitUntilEnd() throws InterruptedException {");
            super.writeInnInnTabln("this.join();");
            super.writeInnInnTabln("return bytes;");
            super.writeInnln("}");
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public void run() {");
            super.writeInnInnTabln("bytes = dump(container);");
            super.writeInnln("}");
        }
        super.writeInnln("public byte[] dump(" + mJavaFQN + " target){");
        super.writeInnInnTabln("return build" + pJavaClass + "(target).toByteArray();");
        super.writeInnln("}");
        this.methods = new LinkedList<>();
        this.messages = new LinkedList<>();
        this.builders = builders;
        this.sJavaFQN = sJavaFQN;
    }

    /**
     * @pre level >= 0
     * @post New root deserializer message pen has been created.
     * @param sJavaPackage for DMP.
     * @param sJavaClass for DMP.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @param builders map from mJavaFQN to his deserialize method FQN.
     * @param parallel if the deserialization must be done in parallel.
     * @return New nested serialize message pen.
     */
    public static SerializerMessagePen build(String sJavaPackage,
            String sJavaClass,
            String mJavaFQN,
            String pJavaClass,
            Map<String, String> builders,
            boolean parallel) {
        SerializerMessagePen pen = new SerializerMessagePen(1,
                sJavaClass,
                sJavaPackage + '.' + sJavaClass,
                mJavaFQN,
                pJavaClass,
                builders,
                parallel);
        return pen;
    }

    /**
     * @pre --
     * @post New nested deserializer message pen has been created.
     * @param mJavaFQN for DMP.
     * @param sJavaClass for DMP.
     * @param pJavaClass for DMP.
     * @param parallel if the deserialization must be done in parallel.
     * @return New nested deserializer message pen.
     */
    public SerializerMessagePen buildInn(String sJavaClass,
            String mJavaFQN,
            String pJavaClass,
            boolean parallel) {
        String sJavaFQNtmp = this.sJavaFQN + '.' + sJavaClass;
        SerializerMessagePen pen = new SerializerMessagePen(level + 1,
                sJavaClass,
                sJavaFQNtmp,
                mJavaFQN,
                pJavaClass,
                builders,
                parallel);
        this.messages.add(pen);
        return pen;
    }

    /**
     * @pre --
     * @post New nested serialize message pen has been created.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @return New nested serialize message pen.
     */
    public SerializerMethodPen addMethod(String mJavaFQN,
            String pJavaClass,
            String pJavaFQN,
            Iterator<MessagePen.Field> fields) throws Exception {
        if (builders.containsKey(pJavaFQN)) {
            throw new Exception("Ja existeix un metode que retorna " + pJavaFQN);
        }
        String methodName = "build" + pJavaClass;
        SerializerMethodPen method = SerializerMethodPen.build(level + 1, methodName, mJavaFQN, pJavaFQN, fields);
        methods.add(method);
        builders.put(pJavaFQN, this.sJavaFQN + '.' + methodName);
        return method;
    }

    /**
     * @pre --
     * @post serialize inner methods has been constructed.
     * @param methodsNS Java FQN to FQN serialize message method.
     * @param enumsNS Java FQN to FQN serialize enumeration method.
     * @param mFQN2pFQN Java message to protoc message.
     */
    public void constructMethods(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
        for (SerializerMessagePen smp : messages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
        for (SerializerMethodPen smp : methods) {
            smp.construct(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    /**
     * @pre --
     * @post returns serialize content.
     * @return serialize content.
     */
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
