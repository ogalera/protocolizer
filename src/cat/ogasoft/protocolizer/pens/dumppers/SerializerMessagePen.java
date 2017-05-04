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
 * @brief DESCRIPTION
 */
public class SerializerMessagePen extends Pen {

    private final List<SerializerMessagePen> messages;
    private final Map<String, String> builders;
    private final String sJavaFQN;
    private final List<SerializerMethodPen> methods;

    private SerializerMessagePen(int level, String sJavaClass, String sJavaFQN, String mJavaFQN, String pJavaClass, Map<String, String> builders, boolean parallel) {
        //For each protoc message, we have a public static class...
        super(level, "public static class " + sJavaClass + (parallel ? " extends Thread implements cat.ogasoft.protocolizer.SerializerProtoWorker<" + mJavaFQN + ">" : "") + " {", "}");
        if (parallel) {
            super.writeInnTabln("private byte[] bytes;");
            super.writeInnTabln("private " + mJavaFQN + " container;");
            super.newLine();
            super.newLine();
            super.writeInnTabln("@Override");
            super.writeInnTabln("public void work(" + mJavaFQN + " container) {");
            super.writeInnInnTabln("this.container = container;");
            super.writeInnInnTabln("super.setName(\"" + sJavaFQN + "Worker\");");
            super.writeInnInnTabln("start();");
            super.writeInnTabln("}");
            super.newLine();
            super.writeInnTabln("@Override");
            super.writeInnTabln("public byte[] waitUntilEnd() throws InterruptedException {");
            super.writeInnInnTabln("this.join();");
            super.writeInnInnTabln("return bytes;");
            super.writeInnTabln("}");
            super.newLine();
            super.writeInnTabln("@Override");
            super.writeInnTabln("public void run() {");
            super.writeInnInnTabln("bytes = dump(container);");
            super.writeInnTabln("}");
        }
        super.writeInnTabln("public byte[] dump(" + mJavaFQN + " target){");
        super.writeInnInnTabln("return build" + pJavaClass + "(target).toByteArray();");
        super.writeInnTabln("}");
        this.methods = new LinkedList<>();
        this.messages = new LinkedList<>();
        this.builders = builders;
        this.sJavaFQN = sJavaFQN;
    }

    public static SerializerMessagePen build(String sJavaPackage, String sJavaClass, String mJavaFQN, String pJavaClass, Map<String, String> builders, boolean parallel) {
        String sJavaFQN = sJavaPackage + '.' + sJavaClass;
        SerializerMessagePen pen = new SerializerMessagePen(1, sJavaClass, sJavaFQN, mJavaFQN, pJavaClass, builders, parallel);
        return pen;
    }

    public SerializerMessagePen buildInn(String sJavaClass, String mJavaFQN, String pJavaClass, boolean parallel) {
        String sJavaFQNtmp = this.sJavaFQN + '.' + sJavaClass;
        SerializerMessagePen pen = new SerializerMessagePen(level + 1, sJavaClass, sJavaFQNtmp, mJavaFQN, pJavaClass, builders, parallel);
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
