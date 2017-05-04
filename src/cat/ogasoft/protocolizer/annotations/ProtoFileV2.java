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
package cat.ogasoft.protocolizer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:22:03 PM]
 *
 * @brief Annotations thats represents a protoc file.
 */
public abstract class ProtoFileV2 {

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface Compiler {

        boolean compile() default true;

        String command();

        Language language() default Language.JAVA;

        public static enum Language {
            JAVA("--java_out"),
            CPP("--cpp_out");
            public final String option;

            private Language(String option) {
                this.option = option;
            }

        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface File {

        String pJavaName(); //<The name of the Java calss generated after protoc compilation (pJavaClass).

        String pJavaPackage() default "cat.ogasoft.protocolizer.messages"; //<The package thats will contains the messages of the protocol.

        boolean generateSource() default true; //<Indicates if the protoc must be created.

        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @Repeatable(Imports.class)
        @interface Import {

            public static enum ACCESS {
                NONE("none"),
                WEAK("weak"),
                PUBLIC("public");
                private final String name;

                private ACCESS(String name) {
                    this.name = name;
                }

                public String getName() {
                    return name;
                }
            }

            ACCESS access() default ACCESS.NONE;

            Class<?> importClass(); //<Name of the import.
        }

        /**
         * @brief Imports container.
         */
        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Imports {

            Import[] value();
        }

        /**
         * @brief An option in the protoc file.
         */
        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Option {

            String name(); //<The name of the option.

            String value(); //<The value for the option.
        }

        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Message {

            String name() default ""; //<Name of the message.

            String comment() default ""; //<Comment for message.

            boolean parallel() default false;

            /**
             * @brief Describes the properties of a field inside a message.
             */
            @Retention(RetentionPolicy.SOURCE)
            @Target(ElementType.FIELD)
            @interface Field {

                /**
                 * @brief Set of types allowed by protocol buffer v2.
                 */
                public static enum DataType {
                    CALCULATED(null, null, null),
                    COMPOSED(null, null, "!= null"),
                    DOUBLE("double", "Double", "!= 0.0"),
                    FLOAT("float", "Float", "!= 0.0"),
                    INT32("int", "Integer", "!= 0"),
                    INT64("int", "Integer", "!= 0"),
                    UINT32("int", "Integer", "!= 0"),
                    UINT64("long", "Long", "!= 0"),
                    SINT32("int", "Integer", "!= 0"),
                    SINT64("long", "Long", "!= 0"),
                    FIXED32("int", "Integer", "!= 0"),
                    FIXED64("long", "Long", "!= 0"),
                    SFIXED32("int", "Integer", "!= 0"),
                    SFIXED64("long", "Long", "!= 0"),
                    BOOL("boolean", "Boolean", ""),
                    STRING("String", null, "!= null"),
                    BYTES("ByteString", null, "!= null");

                    public final String type;
                    public final String classType;
                    public final String cPositive;

                    private DataType(String type, String classType, String cPositive) {
                        this.type = type;
                        this.classType = classType;
                        this.cPositive = cPositive;
                    }

                    public void validate(String type) throws Exception {
                        if (this.type != null && !this.type.equals(type) && !this.classType.equals(type)) {
                            throw new Exception("Type missmatch, me: " + type + ", his: " + this.type + " or " + this.classType);
                        }
                    }

                    public static DataType calculate(String type) throws Exception {
                        if (type == null) {
                            return COMPOSED;
                        }
                        if (type.equals(DOUBLE.type) || type.equals(DOUBLE.classType)) {
                            return DOUBLE;
                        }
                        if (type.equals(FLOAT.type) || type.equals(FLOAT.classType)) {
                            return FLOAT;
                        }
                        if (type.equals(INT32.type) || type.equals(INT32.classType)) {
                            return INT32;
                        }
                        if (type.equals(INT64.type) || type.equals(INT64.classType)) {
                            return INT64;
                        }
                        if (type.equals(SINT32.type) || type.equals(SINT32.classType)) {
                            return SINT32;
                        }
                        if (type.equals(SINT64.type) || type.equals(SINT64.classType)) {
                            return SINT64;
                        }
                        if (type.equals(FIXED32.type) || type.equals(FIXED32.classType)) {
                            return FIXED32;
                        }
                        if (type.equals(FIXED64.type) || type.equals(FIXED64.classType)) {
                            return FIXED64;
                        }
                        if (type.equals(BOOL.type) || type.equals(BOOL.classType)) {
                            return BOOL;
                        }
                        if (type.equals(STRING.type)) {
                            return STRING;
                        }
                        if (type.equals(BYTES.type)) {
                            return BYTES;
                        }
                        return COMPOSED;
                    }

                    public String type() {
                        return type;
                    }
                }

                /**
                 * @brief Set of field rules allowed by protocol buffer v2.
                 */
                public static enum Label {
                    REQUIRED("required"),
                    OPTIONAL("optional"),
                    REPEATED("repeated");
                    public final String name;

                    private Label(String name) {
                        this.name = name;
                    }

                }

                Label label() default Label.REQUIRED; //<Label of the field.

                DataType type() default DataType.CALCULATED; //<The type of the field.

                String name() default ""; //<Name of the field.

                String comment() default ""; //<Comment for the field.

                /**
                 * @brief A field option
                 */
                @Retention(RetentionPolicy.SOURCE)
                @Target(ElementType.FIELD)
                @Repeatable(Options.class)
                @interface Option {

                    String name(); //<The name of the option.

                    String value(); //<The value for the option.
                }

                /**
                 * @brief Field options container.
                 */
                @Retention(RetentionPolicy.SOURCE)
                @Target(ElementType.FIELD)
                public @interface Options {

                    Option[] value();
                }

            }
        }

        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Enum {

            String name() default ""; //<Enum name.
        }

    }

    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface Dumpper {

        public static enum DumpperTypes {
            SERIALIZER,
            DESERIALIZER,
            BOTH;
        }

        String root() default "src";

        boolean parallel() default false;

        DumpperTypes type() default DumpperTypes.BOTH;
    }

    public static enum AnnotationTypes {
        Message,
        Enum,
        ProtoFileV2,
        Import,
        Option,
        Field;
    }
}
