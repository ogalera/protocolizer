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
 * @brief Functional class that contains Protocolizer annotations.
 */
public abstract class ProtoFileV2 {

    /**
     * @brief Annotation for Protocol Buffer compiler.
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface Compiler {

        boolean compile() default true; //<If the message must be compiled.

        String command(); //<The Protocol buffer compiler path.

        Language language() default Language.JAVA; //<Target language for compilation results.

        public static enum Language {
            JAVA("--java_out");
            public final String option; //<Option for compile language.

            private Language(String option) {
                this.option = option;
            }

        }
    }

    /**
     * @brief Annotation for a Protocol Buffer File.
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface File {

        String pJavaName(); //<The name of the Java calss generated after protoc compilation (pJavaClass).

        String pJavaPackage() default "cat.ogasoft.protocolizer.messages"; //<The package thats will contains protoc messages.

        boolean generateSource() default true; //<Indicates if the protoc must be created.

        /**
         * @brief Annotation to import another protoc file.
         */
        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @Repeatable(Imports.class)
        @interface Import {

            public static enum ACCESS {
                NONE("none"), //WEAK("weak"),
                //PUBLIC("public")
                ;
                private final String type; //<Text for import type.

                private ACCESS(String name) {
                    this.type = name;
                }

                public String getName() {
                    return type;
                }
            }

            ACCESS access() default ACCESS.NONE; //<Import type.

            Class<?> importClass(); //<Imported message.
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

            String name(); //<Option name.

            String value(); //<Option value.
        }

        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Message {

            String name() default ""; //<Message name.

            String comment() default ""; //<Message comment.

            boolean parallel() default false; //<If this message must be dumpped in parallel. For this, root message must be annotated with Dumpper annotation.

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
                    CALCULATED(null, null, null), //<The type will be calculated.
                    COMPOSED(null, null, "!= null"), //<Composed type.
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

                    public final String type; //<Type name.
                    public final String classType; //<Equivalent Java class.
                    public final String cPositive; //<Comparation for non empty.

                    private DataType(String type, String classType, String cPositive) {
                        this.type = type;
                        this.classType = classType;
                        this.cPositive = cPositive;
                    }

                    /**
                     * @pre type is valid.
                     * @post type has been validated. If the type is not valid, exception is raised.
                     * @param type to validate.
                     */
                    public void validate(String type) throws Exception {
                        if (this.type != null && !this.type.equals(type) && !this.classType.equals(type)) {
                            throw new Exception("Type missmatch, me: " + type + ", his: " + this.type + " or " + this.classType);
                        }
                    }

                    /**
                     * @pre type is valid.
                     * @post Returns a DataType equivalent to type.
                     * @param type to calculate.
                     * @return DataType equivalent to type.
                     */
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
                    REQUIRED("required"), //<The field needs a value.
                    OPTIONAL("optional"), //<The value for the field is optional.
                    REPEATED("repeated"); //<The value for the field is a list of items.
                    public final String name; //<Name for the label.

                    private Label(String name) {
                        this.name = name;
                    }

                }

                Label label() default Label.REQUIRED; //<Label of the field.

                DataType type() default DataType.CALCULATED; //<Field type.

                String name() default ""; //<Field name.

                String comment() default ""; //<Field comment.

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

        /**
         * @brief Enumeration type.
         */
        @Retention(RetentionPolicy.SOURCE)
        @Target(ElementType.TYPE)
        @interface Enum {

            String name() default ""; //<Enum name.
        }

    }

    /**
     * @brief Annotation for Serializer and/or Deserializer
     */
    @Retention(RetentionPolicy.SOURCE)
    @Target(ElementType.TYPE)
    public static @interface Dumpper {

        public static enum DumpperTypes {
            SERIALIZER, //<Only serialize code will be generated [Java -> Protocol Buffer]
            DESERIALIZER, //<Only deserialize code will be generated [Protocol Buffer -> Java]
            BOTH; //<Serialize and Deserialize code will be generated.
        }

        String root() default "src"; //<The root for generated source.

        DumpperTypes type() default DumpperTypes.BOTH; //<Dumpper type.
    }
}
