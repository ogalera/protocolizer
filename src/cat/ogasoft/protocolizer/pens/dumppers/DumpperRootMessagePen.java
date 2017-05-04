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
public class DumpperRootMessagePen extends Pen {

    private final String javaFQN;
    private final List<SerializerMessagePen> serializerMessages;
    private final List<DeserializerMessagePen> deserializerMessages;
    private final Map<String, String> builders;

    private DumpperRootMessagePen(String sJavaClass, String javaFQN, Map<String, String> builders) {
        super(0, "public class " + sJavaClass + " {", "}");
        this.serializerMessages = new LinkedList<>();
        this.deserializerMessages = new LinkedList<>();
        this.javaFQN = javaFQN;
        this.builders = builders;
    }

    public static DumpperRootMessagePen build(String sJavaClass, String sJavaFQN, Map<String, String> builders) {
        return new DumpperRootMessagePen(sJavaClass, sJavaFQN, builders);
    }

    public SerializerMessagePen serializerMessage(String sJavaClass,
            String mJavaFQN,
            String pJavaClass,
            boolean parallel) {
        SerializerMessagePen message = SerializerMessagePen.build(javaFQN,
                sJavaClass,
                mJavaFQN,
                pJavaClass,
                builders,
                parallel);
        serializerMessages.add(message);
        return message;
    }

    public DeserializerMessagePen deserializerMessage(String dJavaClass,
            String dJavaFQN,
            String pJavaClass,
            String pJavaFQN,
            boolean parallel) {
        DeserializerMessagePen message = DeserializerMessagePen.build(javaFQN,
                dJavaClass,
                dJavaFQN,
                pJavaClass,
                pJavaFQN,
                builders,
                parallel);
        deserializerMessages.add(message);
        return message;
    }

    public Map<String, String> getBuilders() {
        return builders;
    }

    public void constructSerializerMethods(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
        for (SerializerMessagePen smp : serializerMessages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    public void constructDeserializerMethods(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
        for (DeserializerMessagePen smp : deserializerMessages) {
            smp.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
        }
    }

    public List<String> serializerContent() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (SerializerMessagePen mp : serializerMessages) {
            content.addAll(mp.content());
            content.add("\n");
        }
        super.addEnd(content);
        return content;
    }

    public List<String> deserializerContent() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (DeserializerMessagePen mp : deserializerMessages) {
            content.addAll(mp.content());
            content.add("\n");
        }
        super.addEnd(content);
        return content;
    }

    public Iterator<String> serializerIterator() {
        return serializerContent().iterator();
    }

    public Iterator<String> deserializerIterator() {
        return deserializerContent().iterator();
    }
}
