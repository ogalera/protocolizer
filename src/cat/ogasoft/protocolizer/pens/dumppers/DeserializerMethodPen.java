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
public class DeserializerMethodPen extends Pen {

    private final Iterator<MessagePen.Field> fields;

    public DeserializerMethodPen(int level, String methodName, String mJavaFQN, String pJavaFQN, Iterator<MessagePen.Field> fields) {
        super(level, "public static " + mJavaFQN + " " + methodName + "(" + pJavaFQN + " target){", "}");
        super.writeInnln(mJavaFQN + " result = new " + mJavaFQN + "();");
        this.fields = fields;
    }

    public static DeserializerMethodPen build(int level, String methodName, String mJavaFQN, String pJavaFQN, Iterator<MessagePen.Field> fields) {
        return new DeserializerMethodPen(level, methodName, mJavaFQN, pJavaFQN, fields);
    }

    public DeserializerMethodPen construct(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        while (fields.hasNext()) {
            MessagePen.Field field = fields.next();
            switch (field.label) {
                case REQUIRED:
                    if (field.type == DataType.COMPOSED) {
                        String parameter = enumsNS.get(field.javaFQN);
                        if (parameter != null) {
                            parameter = field.javaFQN + ".valueOf(target." + getGetterJava(field, field.javaName) + "().name())";
                        } else {
                            parameter = methodsNS.get(field.javaFQN) + "(target." + getGetterJava(field, field.javaName) + "())";
                        }
                        super.writeInnln("result." + getSetterProto(field.protoName) + "(" + parameter + ");");
                    } else {
                        super.writeInnln("result." + getSetterProto(field.protoName) + "(target." + getGetterJava(field, field.javaName) + "());");
                    }
                    break;
                case OPTIONAL:
                    super.writeInnln("if(target." + getHasProto(field.protoName) + "()){");
                    if (field.type == DataType.COMPOSED) {
                        String parameter = enumsNS.get(field.javaFQN);
                        if (parameter != null) {
                            parameter = field.javaFQN + ".valueOf(target." + getGetterProto(field.protoName) + "().name())";
                        } else {
                            System.out.println("no parameter");
                            parameter = methodsNS.get(field.javaFQN) + "(target." + getGetterProto(field.protoName) + "())";
                        }
                        super.writeInnInnTabln("result." + getSetterJava(field.javaName) + "(" + parameter + ");");
                    } else {
                        super.writeInnInnTabln("result." + getSetterJava(field.javaName) + "(target." + getGetterProto(field.protoName) + "());");
                    }
                    super.writeInnln("}");
                    break;
                case REPEATED:
                    if (field.type == DataType.COMPOSED) {
                        String javaFQN = field.javaFQN;
                        String type = javaFQN;
                        if (javaFQN.contains("<")) {
                            type = javaFQN.substring(javaFQN.indexOf('<') + 1, javaFQN.lastIndexOf('>'));
                        }
                        super.writeInnln("if(target." + getListProto(field.protoName) + "() != null){");
                        super.writeInnInnTabln("java.util.ArrayList<" + type + "> r = new java.util.ArrayList<>(target." + getCountProto((field.protoName)) + "());");
                        String parameter = enumsNS.get(type);
                        if (parameter != null) {
                            super.writeInnInnTabln("for(" + parameter + " k:target." + getListProto(field.protoName) + "()){");
                            parameter = type + ".valueOf(k.name())";
                        } else {
                            super.writeInnInnTabln("for(" + mFQN2pFQN.get(type) + " k:target." + getListProto(field.protoName) + "()){");
                            parameter = methodsNS.get(type) + "(k)";
                        }
                        super.writeInnInnInnln("r.add(" + parameter + ");");
                        super.writeInnInnTabln("}");
                        super.writeInnInnTabln("result." + getSetterJava(field.javaName) + "(r);");
                        super.writeInnln("}");
                    } else {
                        super.writeInnln("{");
                        super.writeInnInnTabln("java.util.ArrayList<" + field.type.classType + "> r = new java.util.ArrayList<>(target." + getCountProto((field.protoName)) + "());");
                        super.writeInnInnTabln("for(" + field.type.classType + " k:target." + getListProto(field.protoName) + "()){");
                        super.writeInnInnInnln("r.add(k);");
                        super.writeInnInnTabln("}");
                        super.writeInnInnTabln("result." + getSetterJava(field.javaName) + "(r);");
                        super.writeInnln("}");
                    }
                    break;
            }
        }
        super.writeInnln("return result;");
        return this;
    }

    private static String getGetterJava(MessagePen.Field field, String name) {
        return "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String getSetterJava(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String getSetterProto(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    private static String getGetterProto(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    private static String getHasProto(String name) {
        return "has" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    private static String getCountProto(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1)) + "Count";
    }

    private static String getListProto(String name) {
        return "get" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1)) + "List";
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
