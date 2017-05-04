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
package cat.ogasoft.protocolizer.pens.generation;

import cat.ogasoft.protocolizer.exceptions.GenerationException;
import com.google.common.base.Strings;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 20, 2017 [8:56:32 PM]
 *
 * @brief A pen to write Protocol Buffer files.
 */
public class FilePen extends Pen {

    public final FileDescriptor fileDescriptor; //<All info relationated with this file.
    private final List<MessagePen> messages; //<Nested messages.
    private final List<EnumPen> enums; //<Nested enumerations
    private final List<String> imports; //<File imports

    /**
     * @pre --
     * @post Generates a new FilePen with fileDescriptor information
     */
    private FilePen(FileDescriptor fileDescriptor) throws GenerationException {
        super(0);
        if (Strings.isNullOrEmpty(fileDescriptor.pJavaPackage)) {
            throw new GenerationException("You must specify a package for protoc");
        }
        super.writeNoTabln("//Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        super.writeNoTabln("//This class has been generated automatically, please DO NOT EDIT!");
        super.writeNoTabln("//For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        this.fileDescriptor = fileDescriptor;
        this.messages = new LinkedList<>();
        this.enums = new LinkedList<>();
        this.imports = new LinkedList<>();
        super.writeNoTabln("syntax = \"proto2\";");
        super.newLine();
        super.writeNoTabln("option java_package = \"" + fileDescriptor.pJavaPackage + "\";");
        super.newLine();
        super.writeNoTabln("option java_outer_classname = \"" + fileDescriptor.pJavaClass + "\";");
        super.newLine();
    }

    /**
     * @pre --
     * @post new FilePen has been created.
     * @param path
     * @param mJavaPackage
     * @param mJavaClass
     * @param pJavaPackage
     * @param pJavaClass
     * @return new FilePen
     * @throws GenerationException 
     */
    public static FilePen build(String path,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass) throws GenerationException {
        String mJavaFQN = mJavaPackage + '.' + mJavaClass;
        String pJavaFQN = pJavaPackage + '.' + pJavaClass;
        return new FilePen(new FileDescriptor(path, mJavaClass, mJavaPackage, mJavaFQN, pJavaClass, pJavaPackage, pJavaFQN));
    }

    /**
     * @pre --
     * @post new comment has been added.
     * @param line comment.
     * @return actual filePen.
     */
    public FilePen addComment(String line) {
        super.writeNoTabln("//" + line);
        return this;
    }

    /**
     * @pre --
     * @post new package has been added.
     * @param line package.
     * @return actual filePen.
     */
    public FilePen addPackage(String line) {
        super.writeNoTabln("package = \"" + line + "\";");
        return this;
    }

    /**
     * @pre --
     * @post new import with access has been added.
     * @param line import.
     * @return actual filePen.
     */
    public FilePen addImport(String access, String line) {
        imports.add(line);
        return this;
    }

    /**
     * @pre --
     * @post new option has been added.
     * @param name for option.
     * @param value for option.
     * @return actual filePen.
     */
    public FilePen addOption(String name, String value) {
        super.writeNoTabln("option " + name + " = \"" + value + "\";");
        return this;
    }

    /**
     * @pre --
     * @post new nested message has been added.
     * @param mJavaClass for the new nested message.
     * @param pJavaClass for the new nested message.
     * @return actual filePen.
     */
    public MessagePen messagePen(String mJavaClass, String pJavaClass, boolean parallel) {
        MessagePen mp = MessagePen.build(fileDescriptor.mJavaFQN, mJavaClass, fileDescriptor.pJavaFQN, pJavaClass, parallel);
        messages.add(mp);
        return mp;
    }

    /**
     * @pre --
     * @post new nested enumeration has been added.
     * @param mJavaClass for the new nested enumeration.
     * @param pJavaClass for the new nested enumeration.
     * @return actual filePen.
     */
    public EnumPen enumPen(String mJavaClass, String pJavaClass) {
        EnumPen ep = EnumPen.build(super.level, fileDescriptor.mJavaFQN, mJavaClass, fileDescriptor.pJavaFQN, pJavaClass);
        enums.add(ep);
        return ep;
    }

    /**
     * @pre --
     * @post returns an iterator over nested messages.
     * @return an iterator over nested messages.
     */
    public Iterator<MessagePen> messageIterator() {
        return messages.iterator();
    }

    /**
     * @pre --
     * @post returns an iterator over nested enumerations.
     * @return an iterator over nested enumerations.
     */
    public Iterator<EnumPen> enumIterator() {
        return enums.iterator();
    }

    /**
     * @pre FilePen path is valid, mFQN2MessagePen maps all imports to this corresponded file pen.
     * @post file has been dumped.
     * @param mFQN2FilePen map from java FQN to FilePen.
     * @throws IOException if there is any problem at create protoc file.
     */
    public void dump(Map<String, FilePen> mFQN2FilePen) throws IOException {
        dump(new File(fileDescriptor.path + ".proto"), mFQN2FilePen);
    }

    /**
     * @pre file is valid, mFQN2MessagePen maps all imports to this corresponded file pen.
     * @post file has been dumped.
     * @param file where protoc will be created.
     * @param mFQN2FilePen map from java FQN to FilePen.
     * @throws IOException if there is any problem at create protoc file.
     */
    public void dump(File file, Map<String, FilePen> mFQN2FilePen) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> fileLines = super.iterator();
            while (fileLines.hasNext()) {
                writer.write(fileLines.next());
            }
            for (String importt : imports) {
                writer.write("import \"" + mFQN2FilePen.get(importt).fileDescriptor.pJavaClass.replace('.', File.separatorChar) + ".proto\";");
                writer.newLine();
            }
            for (MessagePen message : messages) {
                writer.newLine();
                Iterator<String> messageLines = message.iterator();
                while (messageLines.hasNext()) {
                    writer.write(messageLines.next());
                }
            }
            for (EnumPen enumm : enums) {
                writer.newLine();
                Iterator<String> enumLines = enumm.iterator();
                while (enumLines.hasNext()) {
                    writer.write(enumLines.next());
                }
            }
        }
    }

    /**
     * @brief A representation for a Protocol Buffer file.
     */
    public static final class FileDescriptor {

        public final String path; //<Procol buffer file path.
        public final String mJavaClass; //<Java class associated with this protoc file.
        public final String mJavaPackage; //<Java package associated with this protoc file.
        public final String mJavaFQN; //<Java FQN associated with this protoc file (mJavaClass.mJavaPackage).
        public final String pJavaClass; //<Protocol buffer compiled class associated with this protoc file.
        public final String pJavaPackage; //<Protocol buffer compiled package associated with this protoc file.
        public final String pJavaFQN; //<Protocol buffer compiled FQN associated with this protoc file.

        public FileDescriptor(String path,
                String mJavaClass,
                String mJavaPackage,
                String mJavaFQN,
                String pJavaClass,
                String pJavaPackage,
                String pJavaFQN) {
            this.path = path;
            this.mJavaClass = mJavaClass;
            this.mJavaPackage = mJavaPackage;
            this.mJavaFQN = mJavaFQN;
            this.pJavaClass = pJavaClass;
            this.pJavaPackage = pJavaPackage;
            this.pJavaFQN = pJavaFQN;
        }

    }
}
