package cat.ogasoft.protocolizer.annotations;

import cat.ogasoft.protocolizer.utils.DataType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:22:03 PM]
 *
 * @brief Annotations thats represents a message in the protocol.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Message {

    String source() default "src"; //<The default directory where the code has been allocated.
    
    String javaPackage() default "cat.ogasoft.protocolizer.messages"; //<The package thats will contains the messages of the protocol.

    /**
     * @brief Describes the properties of a field.
     */
    @Target(ElementType.FIELD)
    @interface Field {

        DataType type() default DataType.DEFINED; //<The type of the field, defined indicates that the type is of the original field.

        boolean optional() default false; //<Indicates if the field must or not be optional.

    }
}
