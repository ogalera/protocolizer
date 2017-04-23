package cat.ogasoft.protocolizer.pens;

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
    private final String name;
    private final List<Field> fields;
    private final List<MessagePen> messagePen;
    private final List<EnumPen> enumPen;

    private MessagePen(int level, String name) {
        super(level, "message " + name + " {", "}");
        this.name = name;
        ids = 1;
        messagePen = new LinkedList<>();
        enumPen = new LinkedList<>();
        fields = new LinkedList<>();
    }

    public static MessagePen build(int level, String name) {
        return new MessagePen(level, name);
    }

    public MessagePen messagePen(String name) {
        System.out.println("Message " + name + " level " + (super.level + 1));
        MessagePen mp = MessagePen.build(super.level + 1, name);
        messagePen.add(mp);
        return mp;
    }

    public EnumPen enumPen(String name) {
        EnumPen ep = EnumPen.build(super.level + 1, name);
        enumPen.add(ep);
        return ep;
    }

    public MessagePen addField(MessagePen.Field field) {
        fields.add(field);
        if (!Strings.isNullOrEmpty(field.comment)) {
            super.writeInnTabln(field.label + " " + field.type + " " + field.name + " = " + (this.ids++) + "; //" + Strings.nullToEmpty(field.comment));
        } else {
            super.writeInnTabln(field.label + " " + field.type + " " + field.name + " = " + (this.ids++) + ";");
        }
        return this;
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (MessagePen mp : messagePen) {
            content.addAll(mp.content());
        }
        for (EnumPen ep : enumPen) {
            System.out.println("enum found");
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

    @Override
    public Iterator<String> iterator() {
        return content().iterator();
    }

    public static class Field {

        public final String comment;
        public final String label;
        public final String name;
        public final String type;

        public Field(String comment, String label, String name, String type) {
            this.comment = comment;
            this.label = label;
            this.name = name;
            this.type = type;
        }
    }

    public String getName() {
        return name;
    }
}
