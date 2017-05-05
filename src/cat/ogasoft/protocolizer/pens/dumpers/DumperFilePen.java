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
package cat.ogasoft.protocolizer.pens.dumpers;

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
 * @brief Pen to write dumper files.
 */
public class DumperFilePen extends Pen {

    private final File fileDump; //<file where protoc will be created.
    private DumperRootMessagePen messageRoot; //<Message root for dumper.
    public final FileDescriptor fileDescriptor; //<Main information about this dumper file.

    /**
     * @pre fileDump is a valid target path.
     * @post dumperFilePen has been created.
     * @param fileDump where dumper will be created.
     * @param fileDescriptor main information about this dumper file.
     */
    private DumperFilePen(File fileDump, FileDescriptor fileDescriptor) {
        super(0);
        super.writeNoTabln("//Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        super.writeNoTabln("//This class has been generated automatically, plase");
        super.writeNoTabln("//DO NOT EDIT!");
        super.writeNoTabln("//");
        super.writeNoTabln("//For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        super.newLine();
        super.writeNoTabln("package " + fileDescriptor.sJavaPackage + ";");
        super.newLine();
        super.newLine();
        this.fileDescriptor = fileDescriptor;
        this.fileDump = fileDump;
    }

    /**
     * @pre --
     * @post new comment has been added.
     * @param line comment.
     * @return current dumper file pen.
     */
    public DumperFilePen addComment(String line) {
        super.writeNoTabln("//" + line);
        return this;
    }

    /**
     * @pre fileDump is a valid target path.
     * @post new DumperFilePen has been created.
     * @param fileDump where dumperFilePen will be created.
     * @param sJavaPackage java package for dumper
     * @param sJavaClass java class for dumper.
     * @param descriptor with dumper basic information.
     * @return current DumperFilePen.
     */
    public static DumperFilePen build(File fileDump,
            String sJavaPackage,
            String sJavaClass,
            FilePen.FileDescriptor descriptor) {
        return new DumperFilePen(fileDump, new FileDescriptor(descriptor, sJavaPackage, sJavaClass, sJavaPackage + '.' + sJavaClass));
    }

    /**
     * @pre --
     * @post new dumper root has been created. 
     * @param builders where all serializer - deserializer will be annotated.
     * @return current DumperFilePen.
     */
    public DumperRootMessagePen buildRoot(Map<String, String> builders) {
        this.messageRoot = DumperRootMessagePen.build(fileDescriptor.sJavaClass, fileDescriptor.sJavaFQN, builders);
        return this.messageRoot;
    }

    /**
     * @pre methodsNS, enumsNS and mFQN2pFQN are valid.
     * @post all nested methods for serialize has been created.
     * @param methodsNS translate function from protoc message FQN to java class FQN.
     * @param enumsNS translate function from protoc enumeration FQN to java enumeration FQN.
     * @param mFQN2pFQN translate function from java class FQN to protoc FQN.
     */
    public void constructSerializerMethods(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
        messageRoot.constructSerializerMethods(methodsNS, enumsNS, mFQN2pFQN);
    }

    /**
     * @pre methodsNS, enumsNS and mFQN2pFQN are valid.
     * @post all nested methods for deserialize has been created.
     * @param methodsNS translate function from java class FQN to protoc message FQN.
     * @param enumsNS translate function from java enumeration FQN to protoc enumeration FQN.
     * @param mFQN2pFQN translate function from protoc message FQN to java class FQN.
     */
    public void constructDeserializerMethods(Map<String, String> methodsNS,
            Map<String, String> enumsNS,
            Map<String, String> mFQN2pFQN) {
        messageRoot.constructDeserializerMethods(methodsNS, enumsNS, mFQN2pFQN);
    }

    /**
     * @pre --
     * @post serializers has been written.
     */
    public void dumpSerialize() throws IOException {
        dumpSerialize(fileDump);
    }

    /**
     * @pre --
     * @post deserializers has been written.
     */
    public void dumpDeserialize() throws IOException {
        dumpDeserialize(fileDump);
    }

    /**
     * @pre --
     * @post serializers has been written in file.
     */
    public void dumpSerialize(File file) throws IOException {
        dump(file, messageRoot.serializerIterator());
    }

    /**
     * @pre --
     * @post deserializers has been written in file.
     */
    public void dumpDeserialize(File file) throws IOException {
        dump(file, messageRoot.deserializerIterator());
    }

    /**
     * @pre file is valid
     * @post Dumper file pen content has been written in file.
     * @param file where content will be written in.
     * @param messageLines all content to write.
     */
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

    /**
     * @brief Information for File Pen.
     */
    public static class FileDescriptor {

        public final FilePen.FileDescriptor genFileDescriptor; //<Protoc file descriptor.
        public final String sJavaPackage; //<java package for this file pen.
        public final String sJavaClass; //<java class for this file pen.
        public final String sJavaFQN; //<java FQL for this pen.

        public FileDescriptor(FilePen.FileDescriptor genFileDescriptor,
                String sJavaPackage,
                String sJavaClass,
                String sJavaFQN) {
            this.genFileDescriptor = genFileDescriptor;
            this.sJavaPackage = sJavaPackage;
            this.sJavaClass = sJavaClass;
            this.sJavaFQN = sJavaFQN;
        }

    }
}
