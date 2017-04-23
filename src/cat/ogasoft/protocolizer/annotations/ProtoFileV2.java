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

        String protoFilePaths() default "src.cat.ogasoft.protocolizer.protoc"; //<Path of .proto files.

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

        String protoFilePaths() default "src.cat.ogasoft.protocolizer.protoc";

        String name(); //<The name of the Java calss generated after protoc compilation.

        String javaPackage() default "cat.ogasoft.protocolizer.messages"; //<The package thats will contains the messages of the protocol.

        boolean generateSource() default true; //<Indicates if the protoc must be created.

        @Target(ElementType.TYPE)
        @Repeatable(Imports.class)
        @interface Import {

            public static enum ACCESS {
                NONE,
                WEAK,
                PUBLIC;
            }

            ACCESS access() default ACCESS.NONE;

            String name(); //<Name of the import.
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
                    CALCULATED(null),
                    COMPOSED(null),
                    DOUBLE("double"),
                    FLOAT("float"),
                    INT32("int"),
                    INT64("int"),
                    UINT32("int"),
                    UINT64("long"),
                    SINT32("int"),
                    SINT64("long"),
                    FIXED32("int"),
                    FIXED64("long"),
                    SFIXED32("int"),
                    SFIXED64("long"),
                    BOOL("boolean"),
                    STRING("String"),
                    BYTES("ByteString");

                    private final String type;

                    private DataType(String type) {
                        this.type = type;
                    }

                    public void validate(String type) throws Exception {
                        if (this.type != null && !this.type.equals(type)) {
                            throw new Exception("Type missmatch, me: " + type + ", his: " + this.type);
                        }
                    }

                    public static DataType calculate(String type) throws Exception {
                        if (type == null) {
                            return COMPOSED;
                        }
                        if (type.equals(DOUBLE.type)) {
                            return DOUBLE;
                        }
                        if (type.equals(FLOAT.type)) {
                            return FLOAT;
                        }
                        if (type.equals(INT32.type)) {
                            return INT32;
                        }
                        if (type.equals(INT64.type)) {
                            return INT64;
                        }
                        if (type.equals(SINT32.type)) {
                            return SINT32;
                        }
                        if (type.equals(SINT64.type)) {
                            return SINT64;
                        }
                        if (type.equals(FIXED32.type)) {
                            return FIXED32;
                        }
                        if (type.equals(FIXED64.type)) {
                            return FIXED64;
                        }
                        if (type.equals(BOOL.type)) {
                            return BOOL;
                        }
                        if (type.equals(STRING.type)) {
                            return STRING;
                        }
                        if (type.equals(BYTES.type)) {
                            return BYTES;
                        }
                        throw new Exception("Cannot parse " + type);
                    }
                }

                /**
                 * @brief Set of field rules allowed by protocol buffer v2.
                 */
                public static enum Label {
                    REQUIRED,
                    OPTIONAL,
                    REPEATED;
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
