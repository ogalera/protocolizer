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
package cat.ogasoft.protocolizer.pens.generation;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.annotations.ProtoFileV2.File.Message.Field.DataType;
import cat.ogasoft.protocolizer.annotations.ProtoFileV2.File.Message.Field.Label;
import com.google.common.base.Strings;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 20, 2017 [8:56:53 PM]
 *
 * @brief A pen to write a new Protocol Buffer message.
 */
public class MessagePen extends Pen {

    private int ids; //<Idenfifier counter for nested fields, first field has 1.
    public final String mJavaPackage; //<Message Java package.
    public final String mJavaClass; //<Message Java name.
    public final String mJavaFQN; //<Message Java FQN (mJavaPackage.mJavaClass).
    public final String pJavaPackage; //<Protocol buffer message package.
    public final String pJavaClass;//<Protocol buffer message name.
    public final String pJavaFQN; //<Protocol buffer message FQN (pJavaPackage.pJavaClass).
    public final boolean parallel; //<It message will be dumped in parallel, false if message will not be dumped or must be done sequentialy.
    private final List<Field> fields; //<Message fields.
    private final List<MessagePen> messages; //<Nested messages
    private final List<EnumPen> enums; //<Nested enums

    /**
     * @pre level >= 0
     * @post New Message pen has been generated.
     *
     * @param level for the message.
     * @param mJavaPackage Message Java package
     * @param mJavaClass Message Java name.
     * @param pJavaPackage Protocol buffer message package.
     * @param pJavaClass Protocol buffer message name.
     * @param parallel If message will be dumped in parallel.
     */
    private MessagePen(int level,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass,
            boolean parallel) {
        super(level, "message " + pJavaClass + " {", "}");
        this.mJavaPackage = mJavaPackage;
        this.mJavaClass = mJavaClass;
        this.mJavaFQN = mJavaPackage + '.' + mJavaClass;
        this.pJavaClass = pJavaClass;
        this.pJavaPackage = pJavaPackage;
        this.pJavaFQN = pJavaPackage + '.' + pJavaClass;
        this.parallel = parallel;
        ids = 1;
        messages = new LinkedList<>();
        enums = new LinkedList<>();
        fields = new LinkedList<>();
    }

    /**
     * @pre --
     * @post New root message has been created
     * @param mJavaPackage Message Java package
     * @param mJavaClass Message Java name.
     * @param pJavaPackage Protocol buffer message package.
     * @param pJavaClass Protocol buffer message name.
     * @param parallel If message will be dumped in parallel.
     * @return A new root message.
     */
    public static MessagePen build(String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass, boolean parallel) {
        return build(0, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass, parallel);
    }

    /**
     * @pre --
     * @post New message has been created
     * @param level for message.
     * @param mJavaPackage Message Java package
     * @param mJavaClass Message Java name.
     * @param pJavaPackage Protocol buffer message package.
     * @param pJavaClass Protocol buffer message name.
     * @param parallel If message will be dumped in parallel.
     * @return A new root message.
     */
    private static MessagePen build(int level,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass,
            boolean parallel) {
        return new MessagePen(level, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass, parallel);
    }

    /**
     * @pre --
     * @post New nested message has been created
     * @param mJavaClass Message Java name.
     * @param pJavaClass Protocol buffer message name.
     * @param parallel If message will be dumped in parallel.
     * @return A new root message.
     */
    public MessagePen messagePen(String mJavaClass, String pJavaClass, boolean parallel) {
        MessagePen mp = MessagePen.build(super.level + 1, this.mJavaFQN, mJavaClass, this.pJavaFQN, pJavaClass, parallel);
        messages.add(mp);
        return mp;
    }

    /**
     * @pre --
     * @post New nested enumeration has been created
     * @param mJavaClass Message Java name.
     * @param pJavaClass Protocol buffer message name.
     * @return A new root message.
     */
    public EnumPen enumPen(String mJavaClass, String pJavaClass) {
        EnumPen ep = EnumPen.build(super.level + 1, this.mJavaFQN, mJavaClass, this.pJavaFQN, pJavaClass);
        enums.add(ep);
        return ep;
    }

    /**
     * @pre --
     * @post New nested field has been added
     * @param comment for field
     * @param label for field.
     * @param javaName for field.
     * @param javaFQN for field.
     * @param protoName for field.
     * @param type for field.
     * @param composedType indicates if the field is a composed.
     */
    public MessagePen addField(String comment,
            Label label,
            String javaName,
            String javaFQN,
            String protoName,
            DataType type,
            String composedType) {
        Field field = new Field(comment,
                label,
                javaName,
                javaFQN,
                protoName,
                pJavaFQN + '.' + protoName,
                type,
                composedType);
        String typus = field.type == ProtoFileV2.File.Message.Field.DataType.COMPOSED ? field.composedType : type.name().toLowerCase();
        if (!Strings.isNullOrEmpty(field.comment)) {
            super.writeInnln(field.label.name + " " + typus + " " + field.protoName + " = " + (this.ids++) + "; //" + Strings.nullToEmpty(field.comment));
        } else {
            super.writeInnln(field.label.name + " " + typus + " " + field.protoName + " = " + (this.ids++) + ";");
        }
        fields.add(field);
        return this;
    }

    /**
     * @pre --
     * @post returns a list with all Message content.
     * 
     * @return list with all Message content.
     */
    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (MessagePen mp : messages) {
            content.addAll(mp.content());
        }
        for (EnumPen ep : enums) {
            ep.addBegin(content);
            ep.addContent(content);
            ep.addEnd(content);
        }
        super.addEnd(content);
        return content;
    }

    /**
     * @pre --
     * @post returns a iterator over message fields.
     * @return a iterator over message fields.
     */
    public Iterator<Field> fieldIterator() {
        return fields.iterator();
    }

    /**
     * @pre --
     * @post returns a iterator over nested messages.
     * @return a iterator over nested messages.
     */
    public Iterator<MessagePen> messageIterator() {
        return messages.iterator();
    }

    /**
     * @pre --
     * @post returns a iterator over nested enumerations.
     * @return a iterator over nested enumerations.
     */
    public Iterator<EnumPen> enumIterator() {
        return enums.iterator();
    }

    @Override
    public Iterator<String> iterator() {
        return content().iterator();
    }

    /**
     * @brief Representation for Message field.
     */
    public static class Field {

        public final String comment; //<Field comment.
        public final Label label; //<Field label.
        public final String javaFQN; //<Field javaFQN.
        public final String javaName; //<Field javaName.
        public final String protoFQN; //<Field protoFQN.
        public final String protoName; //<Field protoName.
        public final DataType type; //<Field type.
        public final String composedType; //<If this field is composed.

        public Field(String comment,
                Label label,
                String javaName,
                String javaFQN,
                String protoName,
                String protoFQN,
                DataType type,
                String composedType) {
            this.comment = comment;
            this.label = label;
            this.javaName = javaName;
            this.protoName = protoName;
            this.protoFQN = protoFQN;
            this.type = type;
            this.composedType = composedType;
            this.javaFQN = javaFQN;
        }
    }

}
