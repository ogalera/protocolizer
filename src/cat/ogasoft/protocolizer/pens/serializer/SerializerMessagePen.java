/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.pens.serializer;

import cat.ogasoft.protocolizer.pens.FilePen;
import cat.ogasoft.protocolizer.pens.Pen;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 23, 2017 [10:07:54 PM]
 *
 * @brief DESCRIPTION
 */
public class SerializerMessagePen extends Pen {

    private final List<SerializerMessagePen> messages;

    public SerializerMessagePen(String access, int level, String name) {
        super(level, access + " class " + name + " {", "}");
        messages = new LinkedList<>();
    }

    public static SerializerMessagePen buildPublic(int level, String name) {
        SerializerMessagePen data = new SerializerMessagePen("public", level, name);
        return data;
    }

    public static SerializerMessagePen buildPublicStatic(int level, String name, FilePen.FileDescriptor descriptor) {
        SerializerMessagePen pen = new SerializerMessagePen("public static", level, name);
        pen.writeInnTabln("public byte[] dump(" + descriptor.javaFQN + '.' + name + " target){");
        pen.writeInnInnTabln("return null;");
        pen.writeInnTabln("}");
        return pen;
    }

    public static SerializerMessagePen buildPrivate(int level, String name) {
        SerializerMessagePen pen = new SerializerMessagePen("private", level, name);
        return pen;
    }

    public SerializerMessagePen addRequired(String field) {
        return this;
    }

    public SerializerMessagePen addOptional(String field) {
        return this;
    }

    public SerializerMessagePen addRepeated(String field) {
        return this;
    }

    public SerializerMessagePen messagePen(String name, FilePen.FileDescriptor descriptor) {
        SerializerMessagePen message = SerializerMessagePen.buildPublicStatic(super.level + 1, name, descriptor);
        messages.add(message);
        return message;
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        for (SerializerMessagePen mp : messages) {
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
