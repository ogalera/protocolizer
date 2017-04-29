package cat.ogasoft.protocolizer.pens.serializer;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2.File.Message.Field.DataType;
import cat.ogasoft.protocolizer.pens.generation.MessagePen;
import cat.ogasoft.protocolizer.pens.generation.Pen;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 25, 2017 [3:55:12 PM]
 *
 * @brief DESCRIPTION
 */
public class SerializerMethodPen extends Pen {

    private final Iterator<MessagePen.Field> fields;

    public SerializerMethodPen(int level, String methodName, String mJavaFQN, String pJavaFQN, Iterator<MessagePen.Field> fields) {
        super(level, "public static " + pJavaFQN + " " + methodName + "(" + mJavaFQN + " target){", "}");
        super.writeInnTabln(pJavaFQN + ".Builder builder = " + pJavaFQN + ".newBuilder();");
        this.fields = fields;
    }

    public static SerializerMethodPen build(int level, String methodName, String mJavaFQN, String pJavaFQN, Iterator<MessagePen.Field> fields) {
        return new SerializerMethodPen(level, methodName, mJavaFQN, pJavaFQN, fields);
    }

    public SerializerMethodPen construct(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        while (fields.hasNext()) {
            MessagePen.Field field = fields.next();
            switch (field.label) {
                case REQUIRED:
                    if (field.type == DataType.COMPOSED) {
                        String parameter = enumsNS.get(field.javaFQN);
                        if (parameter != null) {
                            parameter += ".valueOf(target." + getGetterJava(field, field.javaName) + "().name())";
                        } else {
                            parameter = methodsNS.get(mFQN2pFQN.get(field.javaFQN)) + "(target." + getGetterJava(field, field.javaName) + "())";
                        }
                        super.writeInnTabln("builder." + getSetterProto(field.protoName) + "(" + parameter + ");");
                    } else {
                        super.writeInnTabln("builder." + getSetterProto(field.protoName) + "(target." + getGetterJava(field, field.javaName) + "());");
                    }
                    break;
                case OPTIONAL:
                    super.writeInnTabln("if(target." + getGetterJava(field, field.javaName) + "() " + field.type.cPositive + "){");
                    if (field.type == DataType.COMPOSED) {
                        String parameter = enumsNS.get(field.javaFQN);
                        if (parameter != null) {
                            parameter += ".valueOf(target." + getGetterJava(field, field.javaName) + "().name())";
                        } else {
                            parameter = methodsNS.get(mFQN2pFQN.get(field.javaFQN)) + "(target." + getGetterJava(field, field.javaName) + "())";
                        }
                        super.writeInnInnTabln("builder." + getSetterProto(field.protoName) + "(" + parameter + ");");
                    } else {
                        super.writeInnInnTabln("builder." + getSetterProto(field.protoName) + "(target." + getGetterJava(field, field.javaName) + "());");
                    }
                    super.writeInnTabln("}");
                    break;
                case REPEATED:
                    if (field.type == DataType.COMPOSED) {
                        String javaFQN = field.javaFQN;
                        String type = javaFQN;
                        if (javaFQN.contains("<")) {
                            type = javaFQN.substring(javaFQN.indexOf('<') + 1, javaFQN.lastIndexOf('>'));
                        }
                        super.writeInnTabln("for(" + type + " k:target." + getGetterJava(field, field.javaName) + "()){");
                        String parameter = methodsNS.get(mFQN2pFQN.get(type)) + "(k)";
                        super.writeInnInnTabln("builder." + getAdderProto(field.protoName) + "(" + parameter + ");");
                    } else {
                        System.out.println("simple");
                        super.writeInnTabln("for(" + field.type.type + " k:target." + getGetterJava(field, field.javaName) + "()){");
                        super.writeInnInnTabln("builder." + getAdderProto(field.protoName) + "(k);");
                    }
                    super.writeInnTabln("}");
                    break;
            }
        }
        super.writeInnTabln("return builder.build();");
        return this;
    }

    private static String getGetterJava(MessagePen.Field field, String name) {
        return (field.type == DataType.BOOL ? "is" : "get") + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String getSetterProto(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    private static String getAdderProto(String name) {
        return "add" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    private static String cleanProto(String dirty) {
        char[] result = new char[dirty.length()];
        int index = 0;
        int i = 0;
        while (i < dirty.length()) {
            if (dirty.charAt(i) == '_') {
                i++;
                if (i < dirty.length()) {
                    result[index++] = Character.toUpperCase(dirty.charAt(i++));
                }
            } else {
                result[index++] = dirty.charAt(i++);
            }
        }
        return new String(result);
    }

    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        super.addEnd(content);
        return content;
    }
}
