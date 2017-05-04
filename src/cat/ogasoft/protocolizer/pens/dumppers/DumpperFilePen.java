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
package cat.ogasoft.protocolizer.pens.dumppers;

import cat.ogasoft.protocolizer.pens.generation.FilePen;
import cat.ogasoft.protocolizer.pens.generation.Pen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 23, 2017 [5:36:42 PM]
 *
 * @brief DESCRIPTION
 */
public class DumpperFilePen extends Pen {

    private final File fileDump;
    private DumpperRootMessagePen messageRoot;
    public final FileDescriptor fileDescriptor;

    private DumpperFilePen(File fileDump, FileDescriptor fileDescriptor) {
        super(0);
        super.writeln("//Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        super.writeln("//This class has been generated automatically, plase");
        super.writeln("//DO NOT EDIT!");
        super.writeln("//");
        super.writeln("//For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        super.newLine();
        super.writeln("package " + fileDescriptor.sJavaPackage + ";");
        super.newLine();
        super.newLine();
        this.fileDescriptor = fileDescriptor;
        this.fileDump = fileDump;
    }

    public DumpperFilePen addComment(String line) {
        super.writeln("//" + line);
        return this;
    }

    public static DumpperFilePen build(File fileDump,
            String sJavaPackage,
            String sJavaClass,
            FilePen.FileDescriptor descriptor) {
        return new DumpperFilePen(fileDump, new FileDescriptor(descriptor, sJavaPackage, sJavaClass, sJavaPackage + '.' + sJavaClass));
    }

    public DumpperRootMessagePen buildRoot(Map<String, String> builders) {
        this.messageRoot = DumpperRootMessagePen.build(fileDescriptor.sJavaClass, fileDescriptor.sJavaFQN, builders);
        return this.messageRoot;
    }

    public void constructSerializerMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        messageRoot.constructSerializerMethods(methodsNS, enumsNS, mFQN2pFQN);
    }

    public void constructDeserializerMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        messageRoot.constructDeserializerMethods(methodsNS, enumsNS, mFQN2pFQN);
    }

    public void dumpSerialize() throws IOException {
        dumpSerialize(fileDump);
    }

    public void dumpDeserialize() throws IOException {
        dumpDeserialize(fileDump);
    }

    public void dumpSerialize(File file) throws IOException {
        dump(file, messageRoot.serializerIterator());
    }

    public void dumpDeserialize(File file) throws IOException {
        dump(file, messageRoot.deserializerIterator());
    }

    private void dump(File file, Iterator<String> messageLines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> fileLines = iterator();
            while (fileLines.hasNext()) {
                writer.write(fileLines.next());
            }
            while (messageLines.hasNext()) {
                writer.write(messageLines.next());
            }
        }
    }

    public static class FileDescriptor {

        public final FilePen.FileDescriptor genFileDescriptor;
        public final String sJavaPackage;
        public final String sJavaClass;
        public final String sJavaFQN;

        public FileDescriptor(FilePen.FileDescriptor genFileDescriptor, String sJavaPackage, String sJavaClass, String sJavaFQN) {
            this.genFileDescriptor = genFileDescriptor;
            this.sJavaPackage = sJavaPackage;
            this.sJavaClass = sJavaClass;
            this.sJavaFQN = sJavaFQN;
        }

    }
}
