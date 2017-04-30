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

    @Retention(RetentionPolicy.CLASS)
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

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE)
    public static @interface File {

        String pJavaName(); //<The name of the Java calss generated after protoc compilation (pJavaClass).

        String pJavaPackage() default "cat.ogasoft.protocolizer.messages"; //<The package thats will contains the messages of the protocol.

        boolean generateSource() default true; //<Indicates if the protoc must be created.

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
        @Target(ElementType.TYPE)
        @interface Imports {

            Import[] value();
        }

        /**
         * @brief An option in the protoc file.
         */
        @Target(ElementType.TYPE)
        @interface Option {

            String name(); //<The name of the option.

            String value(); //<The value for the option.
        }

        @Target(ElementType.TYPE)
        @interface Message {

            String name() default ""; //<Name of the message.

            String comment() default ""; //<Comment for message.

            /**
             * @brief Describes the properties of a field inside a message.
             */
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
                @Target(ElementType.FIELD)
                @Repeatable(Options.class)
                @interface Option {

                    String name(); //<The name of the option.

                    String value(); //<The value for the option.
                }

                /**
                 * @brief Field options container.
                 */
                @Target(ElementType.FIELD)
                public @interface Options {

                    Option[] value();
                }

            }
        }

        @Target(ElementType.TYPE)
        @interface Enum {

            String name() default ""; //<Enum name.
        }

    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.TYPE)
    public static @interface Serialize {

        String root() default "src";

        String javaPackage() default "cat.ogasoft.protocolizer.serializers";

        boolean parallel() default false;
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
