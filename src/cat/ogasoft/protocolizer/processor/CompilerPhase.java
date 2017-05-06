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
package cat.ogasoft.protocolizer.processor;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.exceptions.CompilerException;
import java.io.File;
import java.io.FileFilter;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:10:19 PM]
 *
 * @brief Functional class for compile protoc files in Java compiled protocol buffer messages.
 */
public abstract class CompilerPhase {

    private static final Logger LOG = LogManager.getLogger();

    public static void processCompiler(RoundEnvironment roundEnv) throws CompilerException {
        try {
            String protocPath = "src" + File.separatorChar
                    + "cat" + File.separatorChar
                    + "ogasoft" + File.separatorChar
                    + "protocolizer" + File.separatorChar
                    + "protoc";

            DefaultExecutor de = new DefaultExecutor();
            for (Element element : roundEnv.getElementsAnnotatedWith(ProtoFileV2.Compiler.class)) {
                ProtoFileV2.Compiler compiler = element.getAnnotation(ProtoFileV2.Compiler.class);
                if (compiler.compile()) {
                    String base = protocPath.replace('.', File.separatorChar);
                    File dst = new File(base);
                    if (!dst.exists() && !dst.mkdirs()) {
                        throw new Exception("Cann not be created directory " + dst.getAbsolutePath());
                    }
                    File rootDirectori = new File(base);
                    //Compile all the files in compiler.protoFilePaths()
                    FileFilter filter = new FileFilter() {
                        @Override
                        public boolean accept(File pathname) {
                            return pathname.getName().toLowerCase().endsWith(".proto");
                        }
                    };
                    for (File protoc : rootDirectori.listFiles(filter)) {
                        String target = base + File.separatorChar + protoc.getName();
                        LOG.info("\tCompiling " + target + "...");
                        CommandLine cmd = CommandLine.parse(compiler.command() + " --proto_path=" + protocPath + " " + compiler.language().option + "=src " + target);
                        int result = de.execute(cmd);
                        if (result != 0) {
                            throw new CompilerException("HO ho... somthing went wrong, code: " + result);
                        }
                        LOG.info("\t" + target + " compiled");
                    }
                }
            }
        } catch (Exception e) {
            //Any exception is a CompilerException.
            throw new CompilerException(e.getMessage());
        }
    }
}
