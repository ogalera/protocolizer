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

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 20, 2017 [8:56:32 PM]
 *
 * @brief A pen to write protoc files.
 */
public class FilePen extends Pen {

    public final FileDescriptor fileDescriptor; //<All the info relationated with this file.
    private final List<MessagePen> messages; //<All inner messages.
    private final List<EnumPen> enums; //<All inner enumerations

    /**
     * @pre --
     * @post Generates a new FilePen with fileDescriptor information
     */
    private FilePen(FileDescriptor fileDescriptor) throws GenerationException {
        super(0);
        if (Strings.isNullOrEmpty(fileDescriptor.pJavaPackage)) {
            throw new GenerationException("You must specify a package for protoc");
        }
        super.writeln("//Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        super.writeln("//This class has been generated automatically, please DO NOT EDIT!");
        super.writeln("//For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        this.fileDescriptor = fileDescriptor;
        this.messages = new LinkedList<>();
        this.enums = new LinkedList<>();
        super.writeln("syntax = \"proto2\";");
        super.newLine();
        super.writeln("option java_package = \"" + fileDescriptor.pJavaPackage + "\";");
        super.newLine();
        super.writeln("option java_outer_classname = \"" + fileDescriptor.pJavaClass + "\";");
        super.newLine();
    }

    public static FilePen build(String path,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass) throws GenerationException {
        String mJavaFQN = mJavaPackage + '.' + mJavaClass;
        String pJavaFQN = pJavaPackage + '.' + pJavaClass;
        return new FilePen(new FileDescriptor(path, mJavaClass, mJavaPackage, mJavaFQN, pJavaClass, pJavaPackage, pJavaFQN));
    }

    public FilePen addComment(String line) {
        super.writeln("//" + line);
        return this;
    }

    public FilePen addPackage(String line) {
        super.writeln("package = \"" + line + "\";");
        return this;
    }

    public FilePen addImport(String access, String line) {
        super.writeln("import " + access + " \"" + line + "\";");
        return this;
    }

    public FilePen addOption(String name, String value) {
        super.writeln("option " + name + " = \"" + value + "\";");
        return this;
    }

    public MessagePen messagePen(String mJavaClass, String pJavaClass) {
        MessagePen mp = MessagePen.build(fileDescriptor.mJavaFQN, mJavaClass, fileDescriptor.pJavaFQN, pJavaClass);
        messages.add(mp);
        return mp;
    }

    public EnumPen enumPen(String mJavaClass, String pJavaClass) {
        EnumPen ep = EnumPen.build(super.level, fileDescriptor.mJavaFQN, mJavaClass, fileDescriptor.pJavaFQN, pJavaClass);
        enums.add(ep);
        return ep;
    }

    public Iterator<MessagePen> messageIterator() {
        return messages.iterator();
    }
    
    public Iterator<EnumPen> enumIterator() {
        return enums.iterator();
    }

    public void dump(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> fileLines = super.iterator();
            while (fileLines.hasNext()) {
                writer.write(fileLines.next());
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

    public static final class FileDescriptor {

        public final String path;
        public final String mJavaClass;
        public final String mJavaPackage;
        public final String mJavaFQN;
        public final String pJavaClass;
        public final String pJavaPackage;
        public final String pJavaFQN;

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
