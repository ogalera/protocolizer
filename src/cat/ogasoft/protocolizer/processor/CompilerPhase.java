package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.exceptions.CompilerException;
import java.io.File;
import java.io.FileFilter;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:10:19 PM]
 *
 * @brief DESCRIPTION
 */
public class CompilerPhase {

    public static void processCompiler(RoundEnvironment roundEnv) throws CompilerException {
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.Compiler.class)) {
                ProtoFileV2.Compiler compiler = element.getAnnotation(ProtoFileV2.Compiler.class);
                if (compiler.compile()) {
                    File dst = new File(compiler.protoFilePaths().replace('.', File.separatorChar));
                    if (!dst.exists() && !dst.mkdirs()) {
                        throw new Exception("Cann not be created directory " + dst.getAbsolutePath());
                    }
                    String base = compiler.protoFilePaths().replace('.', File.separatorChar);
                    File rootDirectori = new File(base);
                    //Compile all the files in compiler.protoFilePaths()
                    FileFilter filter = new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.getName().toLowerCase().endsWith(".proto");
                        }
                    };
                    DefaultExecutor de = new DefaultExecutor();
                    for (File protoc : rootDirectori.listFiles(filter)) {
                        CommandLine cmd = CommandLine.parse(compiler.command() + " --proto_path=src " + compiler.language().option + "=src " + base + File.separatorChar + protoc.getName());
                        int result = de.execute(cmd);
                        if (result != 0) {
                            throw new CompilerException("HO ho... somthing went wrong, code: " + result);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new CompilerException(e.getMessage());
        }
    }
}
