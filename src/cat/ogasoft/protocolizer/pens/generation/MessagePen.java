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
 * @brief DESCRIPTION
 */
public class MessagePen extends Pen {

    private int ids;
    public final String mJavaPackage;
    public final String mJavaClass;
    public final String mJavaFQN;
    public final String pJavaPackage;
    public final String pJavaClass;
    public final String pJavaFQN;
    private final List<Field> fields;
    private final List<MessagePen> messages;
    private final List<EnumPen> enums;

    private MessagePen(int level, String mJavaPackage, String mJavaClass, String pJavaPackage, String pJavaClass) {
        super(level, "message " + pJavaClass + " {", "}");
        this.mJavaPackage = mJavaPackage;
        this.mJavaClass = mJavaClass;
        this.mJavaFQN = mJavaPackage + '.' + mJavaClass;
        this.pJavaClass = pJavaClass;
        this.pJavaPackage = pJavaPackage;
        this.pJavaFQN = pJavaPackage + '.' + pJavaClass;
        ids = 1;
        messages = new LinkedList<>();
        enums = new LinkedList<>();
        fields = new LinkedList<>();
    }

    public static MessagePen build(String mJavaPackage, String mJavaClass, String pJavaPackage, String pJavaClass) {
        return build(0, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass);
    }

    private static MessagePen build(int level, String mJavaPackage, String mJavaClass, String pJavaPackage, String pJavaClass) {
        return new MessagePen(level, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass);
    }

    public MessagePen messagePen(String mJavaClass, String pJavaClass) {
        MessagePen mp = MessagePen.build(super.level + 1, this.mJavaFQN, mJavaClass, this.pJavaFQN, pJavaClass);
        messages.add(mp);
        return mp;
    }

    public EnumPen enumPen(String mJavaClass, String pJavaClass) {
        EnumPen ep = EnumPen.build(super.level + 1, this.mJavaFQN, mJavaClass, this.pJavaFQN, pJavaClass);
        enums.add(ep);
        return ep;
    }

    public MessagePen addField(String comment, Label label, String javaName, String javaFQN, String protoName, DataType type, String composedType) {
        Field field = new Field(comment, label, javaName, javaFQN, protoName, type, composedType);
        String typus = field.type == ProtoFileV2.File.Message.Field.DataType.COMPOSED ? field.composedType : type.name().toLowerCase();
        if (!Strings.isNullOrEmpty(field.comment)) {
            super.writeInnTabln(field.label.name + " " + typus + " " + field.protoName + " = " + (this.ids++) + "; //" + Strings.nullToEmpty(field.comment));
        } else {
            super.writeInnTabln(field.label.name + " " + typus + " " + field.protoName + " = " + (this.ids++) + ";");
        }
        fields.add(field);
        return this;
    }

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

    public Iterator<Field> fieldIterator() {
        return fields.iterator();
    }

    public Iterator<MessagePen> messageIterator() {
        return messages.iterator();
    }

    public Iterator<EnumPen> enumIterator() {
        return enums.iterator();
    }

    @Override
    public Iterator<String> iterator() {
        return content().iterator();
    }

    public static class Field {

        public final String comment;
        public final Label label;
        public final String javaFQN;
        public final String javaName;
        public final String protoName;
        public final DataType type;
        public final String composedType;

        public Field(String comment, Label label, String javaName, String javaFQN, String protoName, DataType type, String composedType) {
            this.comment = comment;
            this.label = label;
            this.javaName = javaName;
            this.protoName = protoName;
            this.type = type;
            this.composedType = composedType;
            this.javaFQN = javaFQN;
        }
    }

}
