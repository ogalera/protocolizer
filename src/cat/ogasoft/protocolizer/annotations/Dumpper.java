package cat.ogasoft.protocolizer.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:44:29 PM]
 * 
 * @brief Annotation to generate a dumpper that can serialize/deserialize
 * the data.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Dumpper {
    String source() default "src"; //<Where the code has been allocated.
    
    String javaPackage() default "cat.ogasoft.protocolizer.dumppers"; //The package where the dumppers must be allocated.
    
    boolean serializer() default true; //<Indicates if the serializer must be or not created.
    
    boolean deserializer() default true; //<Indicates if the deserializer must be or not created.
    
    boolean force() default true; //<Recreate the serializer/deserializer.
    
    /**
     * @brief Indicates if the process must be done in parallel or not.
     */
    boolean parallel() default false;
}
