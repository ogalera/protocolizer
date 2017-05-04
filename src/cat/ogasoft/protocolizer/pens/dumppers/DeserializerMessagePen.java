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
package cat.ogasoft.protocolizer.pens.dumppers;

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
 * @brief A pen to write deserialize protoc messages.
 */
public class DeserializerMessagePen extends Pen {

    private final List<DeserializerMessagePen> messages; //<Nested messages.
    private final Map<String, String> builders;
    private final String dJavaFQN; //<deserialize Java FQN
    private final String pJavaFQN; //<Protocol buffer java FQN.
    private final List<DeserializerMethodPen> methods; //<Nested methods to deserialize nested messages.

    /**
     * @pre level >= 0
     * @post New deserialize message pen has been created.
     * @param level for DMP.
     * @param dJavaClass for DMP.
     * @param dJavaFQN for  DMP.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @param pJavaFQN for DMP.
     * @param builders map from mJavaFQN to his deserialize method FQN.
     * @param parallel if the deserialization must be done in parallel.
     */
    private DeserializerMessagePen(int level,
            String dJavaClass,
            String dJavaFQN,
            String mJavaFQN,
            String pJavaClass,
            String pJavaFQN,
            Map<String, String> builders,
            boolean parallel) {
        //For each protoc message, we have a public static class...
        super(level, "public static class " + dJavaClass + (parallel ? " extends Thread implements cat.ogasoft.protocolizer.DeserializerProtoWorker<" + mJavaFQN + ">" : "") + " {", "}");
        if (parallel) {
            super.writeInnln("private byte[] bytes;");
            super.writeInnln("private " + mJavaFQN + " container;");
            super.writeInnln("private String errMsg;");
            super.newLine();
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public void work(byte[] bytes) {");
            super.writeInnInnTabln("super.setName(\"" + dJavaFQN + "Worker\");");
            super.writeInnInnTabln("this.bytes = bytes;");
            super.writeInnInnTabln("start();");
            super.writeInnln("}");
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public " + mJavaFQN + " waitUntilEnd() throws InterruptedException,cat.ogasoft.protocolizer.exceptions.DeserializationException {");
            super.writeInnInnTabln("this.join();");
            super.writeInnInnTabln("if(container == null){");
            super.writeInnInnInnln("throw new cat.ogasoft.protocolizer.exceptions.DeserializationException(\"Deserialization exception, message [\" + errMsg + \"]\");");
            super.writeInnInnTabln("}");
            super.writeInnInnTabln("return container;");
            super.writeInnln("}");
            super.newLine();
            super.writeInnln("@Override");
            super.writeInnln("public void run() {");
            super.writeInnInnTabln("try{");
            super.writeInnInnInnln("container = dump(bytes);");
            super.writeInnInnTabln("} catch (com.google.protobuf.InvalidProtocolBufferException e) {");
            super.writeInnInnInnln("errMsg = e.getMessage();");
            super.writeInnInnTabln("}");
            super.writeInnln("}");
        }
        super.writeInnln("public " + mJavaFQN + " dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {");
        super.writeInnInnTabln(pJavaFQN + " result = " + pJavaFQN + ".parseFrom(target);");
        super.writeInnInnTabln("return build" + pJavaClass + "(result);");
        super.writeInnln("}");
        super.newLine();
        this.methods = new LinkedList<>();
        this.messages = new LinkedList<>();
        this.builders = builders;
        this.dJavaFQN = dJavaFQN;
        this.pJavaFQN = pJavaFQN;
    }

    /**
     * @pre level >= 0
     * @post New root deserializer message pen has been created.
     * @param dJavaPackage for DMP.
     * @param dJavaClass for DMP.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @param pJavaFQN for DMP.
     * @param builders map from mJavaFQN to his deserialize method FQN.
     * @param parallel if the deserialization must be done in parallel.
     */
    public static DeserializerMessagePen build(String dJavaPackage,
            String dJavaClass,
            String mJavaFQN,
            String pJavaClass,
            String pJavaFQN,
            Map<String, String> builders,
            boolean parallel) {
        String sJavaFQN = dJavaPackage + '.' + dJavaClass;
        DeserializerMessagePen pen = new DeserializerMessagePen(1,
                dJavaClass,
                sJavaFQN,
                mJavaFQN,
                pJavaClass,
                pJavaFQN,
                builders,
                parallel);
        return pen;
    }

    /**
     * @pre --
     * @post New nested deserializer message pen has been created.
     * @param mJavaFQN for DMP.
     * @param dJavaClass for DMP.
     * @param pJavaClass for DMP.
     * @param parallel if the deserialization must be done in parallel.
     * @return New nested deserializer message pen.
     */
    public DeserializerMessagePen buildInn(String dJavaClass,
            String mJavaFQN,
            String pJavaClass,
            boolean parallel) {
        DeserializerMessagePen pen = new DeserializerMessagePen(level + 1,
                dJavaClass,
                this.dJavaFQN + '.' + dJavaClass,
                mJavaFQN,
                pJavaClass,
                pJavaFQN + '.' + pJavaClass,
                builders,
                parallel);
        this.messages.add(pen);
        return pen;
    }

    /**
     * @pre --
     * @post New nested deserializer message pen has been created.
     * @param mJavaFQN for DMP.
     * @param pJavaClass for DMP.
     * @return New nested deserializer message pen.
     */
    public DeserializerMethodPen addMethod(String mJavaFQN,
            String pJavaClass,
            String pJavaFQN,
            Iterator<MessagePen.Field> fields) throws Exception {
        if (builders.containsKey(mJavaFQN)) {
            throw new Exception("Ja existeix un metode que retorna " + mJavaFQN);
        }
        String methodName = "build" + pJavaClass;
        DeserializerMethodPen method = DeserializerMethodPen.build(level + 1, methodName, mJavaFQN, pJavaFQN, fields);
        methods.add(method);
        builders.put(mJavaFQN, this.dJavaFQN + '.' + methodName);
        return method;
    }

    public void constructMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        for (DeserializerMessagePen smp : messages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
        for (DeserializerMethodPen smp : methods) {
            smp.construct(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (DeserializerMessagePen smp : messages) {
            content.addAll(smp.content());
        }
        for (DeserializerMethodPen mp : methods) {
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
