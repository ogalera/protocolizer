/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.pens.serializer;

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
        data.newLine();
        data.writeInnTabln("public byte[] dump(Object obj){");
        data.writeInnTabln("return null;");
        data.writeInnTabln("}");
        data.newLine();
        return data;
    }

    public static SerializerMessagePen buildPrivate(int level, String name) {
        return new SerializerMessagePen("private", level, name);
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

    public SerializerMessagePen messagePen(String name) {
        SerializerMessagePen message = SerializerMessagePen.buildPrivate(super.level + 1, name);
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
