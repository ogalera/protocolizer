package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.annotations.Dumpper;
import cat.ogasoft.protocolizer.annotations.Message;
import cat.ogasoft.protocolizer.exceptions.ProcessException;
import cat.ogasoft.protocolizer.utils.Classer;
import java.io.File;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * @author Oscar Galera i Alfaro
 * @date Mar 19, 2017 [2:20:14 PM]
 *
 * @brief Processor that generates all the files that composes the protocol.
 */
@SupportedAnnotationTypes({"cat.ogasoft.protocolizer.annotations.Message",
    "cat.ogasoft.protocolizer.annotations.Dumpper"})
public class Processor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean processed = true;
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(Message.class)) {
                //First of all, we need to generate a protocol buffer field (the message).
                generateProtoc(element);

                //Do we need to create a dumpper for serialize/deserialize?
                Dumpper dumpper = element.getAnnotation(Dumpper.class);
                if (dumpper != null) {
                    generateDumppers(element, dumpper);
                }
            }
        } catch (Throwable t) {
            System.out.println(t.getMessage());
            processed = false;
        }
        return processed;
    }

    private void generateProtoc(Element element) throws Exception {
        System.out.println("Protoc per " + element.getClass().getName());
    }

    /**
     * @pre Directory of the dumppers exists or can be created.
     * @post Has been created the dumppers for the element.
     */
    private void generateDumppers(Element element, Dumpper dumpper) throws ProcessException {
        File pack = new File(dumpper.source()+File.separatorChar+dumpper.javaPackage().replace('.', File.separatorChar));
        if (!pack.exists()) {
            if (!pack.mkdir()) {
                throw new ProcessException("Dumpper directory [" + pack.getAbsolutePath() + "] can not be created");
            }
        }
        if (dumpper.serializer()) {
            //We need a serializer!
            dumpperSerialize(dumpper, element.getSimpleName().toString());
        }
    }

    /**
     * @pre Package that contains the dumpper already exists.
     * @post Has been created a serializer for the message.
     */
    private static void dumpperSerialize(Dumpper dumpper, String name) throws ProcessException {
        name += "Serializer";
        File serializer = new File(dumpper.source()+File.separatorChar+dumpper.javaPackage().replace('.', File.separatorChar) + File.separatorChar + name + ".java");
        serializer.delete();
        try {
            if (!serializer.createNewFile()) {
                throw new Exception("The file " + serializer.getAbsolutePath() + " can not been created");
            }
            //Crear el serialitzador...
            Classer classer = new Classer(dumpper.javaPackage(), name);
            classer.addHeaderNewLine();
            classer.addHeaderLine("[Protocoler]");
            classer.addHeaderLine("This class serializes data for data model XXX");
            classer.write(serializer);
            System.out.println("ESCRIT");
        } catch (Exception e) {
            throw new ProcessException("Error at create the serializer, message: " + e.getMessage());
        }
    }
}
