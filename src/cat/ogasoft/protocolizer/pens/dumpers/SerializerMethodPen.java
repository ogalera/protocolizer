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
package cat.ogasoft.protocolizer.pens.dumpers;

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
 * @brief A pen to write serialize methods for protoc messages.
 */
public class SerializerMethodPen extends Pen {

    private final Iterator<MessagePen.Field> fields; //<Message fields.

    /**
     * @pre level >= 0
     * @post serializer method has been created.
     * @param level serializer
     * @param methodName method name.
     * @param mJavaFQN method return.
     * @param pJavaFQN method parameter.
     * @param fields of the message.
     */
    public SerializerMethodPen(int level,
            String methodName,
            String mJavaFQN,
            String pJavaFQN,
            Iterator<MessagePen.Field> fields) {
        super(level, "public static " + pJavaFQN + " " + methodName + "(" + mJavaFQN + " target){", "}");
        super.writeInnln(pJavaFQN + ".Builder builder = " + pJavaFQN + ".newBuilder();");
        this.fields = fields;
    }

    /**
     * @pre level >= 0
     * @post new SerializerMethodPen has been created
     * @param level of the method.
     * @param methodName name for the method.
     * @param mJavaFQN method returns type.
     * @param pJavaFQN method parameter.
     * @param fields deserializer message fields.
     * @return new DeserializerMethodPen.
     */
    public static SerializerMethodPen build(int level,
            String methodName,
            String mJavaFQN,
            String pJavaFQN,
            Iterator<MessagePen.Field> fields) {
        return new SerializerMethodPen(level, methodName, mJavaFQN, pJavaFQN, fields);
    }

    /**
     * @pre methodsNS, enumsNS and mFQN2pFQN are completed.
     * @post current method has been constructed.
     * @param methodsNS namespace for all methods.
     * @param enumsNS namespace for all enumerations.
     * @param mFQN2pFQN translate function from message FQN to protoc FQN.
     * @return current method pen constructed.
     */
    public SerializerMethodPen construct(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
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
                        super.writeInnln("builder." + getSetterProto(field.protoName) + "(" + parameter + ");");
                    } else {
                        super.writeInnln("builder." + getSetterProto(field.protoName) + "(target." + getGetterJava(field, field.javaName) + "());");
                    }
                    break;
                case OPTIONAL:
                    super.writeInnln("if(target." + getGetterJava(field, field.javaName) + "() " + field.type.cPositive + "){");
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
                    super.writeInnln("}");
                    break;
                case REPEATED:
                    super.writeInnln("if(target." + getGetterJava(field, field.javaName) + "() != null){");
                    if (field.type == DataType.COMPOSED) {
                        String javaFQN = field.javaFQN;
                        String type = javaFQN;
                        if (javaFQN.contains("<")) {
                            type = javaFQN.substring(javaFQN.indexOf('<') + 1, javaFQN.lastIndexOf('>'));
                        }
                        super.writeInnInnTabln("if(target." + getGetterJava(field, field.javaName) + "() != null){");
                        super.writeInnInnInnln("for(" + type + " k:target." + getGetterJava(field, field.javaName) + "()){");
                        String parameter = enumsNS.get(type);
                        if (parameter != null) {
                            parameter += ".valueOf(k.name())";
                        } else {
                            parameter = methodsNS.get(mFQN2pFQN.get(type)) + "(k)";
                        }
                        super.writeInnInnInnInnln("builder." + getAdderProto(field.protoName) + "(" + parameter + ");");
                        super.writeInnInnInnln("}");
                        super.writeInnInnTabln("}");
                    } else {
                        super.writeInnInnTabln("for(" + field.type.classType + " k:target." + getGetterJava(field, field.javaName) + "()){");
                        super.writeInnInnInnln("builder." + getAdderProto(field.protoName) + "(k);");
                        super.writeInnInnTabln("}");
                    }
                    super.writeInnln("}");
                    break;
            }
        }
        super.writeInnln("return builder.build();");
        return this;
    }

    /**
     * @pre --
     * @post getter for name in java format.
     * @param name for getter.
     * @return getter for name in java format
     */
    private static String getGetterJava(MessagePen.Field field, String name) {
        return (field.type == DataType.BOOL ? "is" : "get") + name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    /**
     * @pre --
     * @post setter for name in protocol buffer format.
     * @param name for setter.
     * @return setter for name in protocol buffer format.
     */
    private static String getSetterProto(String name) {
        return "set" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    /**
     * @pre --
     * @post add for name in protocol buffer format.
     * @param name for add.
     * @return add for name in protocol buffer format.
     */
    private static String getAdderProto(String name) {
        return "add" + name.substring(0, 1).toUpperCase() + cleanProto(name.substring(1));
    }

    /**
     * @pre --
     * @post returns dirty in protoc format.
     * @para dirty to clean.
     * @return dirty in protoc format.
     */
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

    /**
     * @pre --
     * @post returns deserializerMethodPen content.
     * @return deserializerMethodPen content.
     */
    public List<String> content() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        super.addEnd(content);
        return content;
    }
}
