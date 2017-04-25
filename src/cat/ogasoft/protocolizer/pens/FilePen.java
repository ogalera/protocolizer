package cat.ogasoft.protocolizer.pens;

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

    public final FileDescriptor fileDescriptor; //<The java class associated with target protoc.
    private final List<MessagePen> messages;
    private final List<EnumPen> enums;

    private FilePen(String javaFQN, String protocFQN) {
        super(0);
        this.fileDescriptor = new FileDescriptor(javaFQN, protocFQN);

        this.messages = new LinkedList<>();
        this.enums = new LinkedList<>();
        addComment("Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        addComment("This class has been generated automatically, please DO NOT EDIT!");
        addComment("For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        super.writeln("syntax = \"proto2\";");
        super.newLine();
    }

    public static FilePen build(String javaFQN, String protocFQN) {
        return new FilePen(javaFQN, protocFQN);
    }

    public FilePen addComment(String line) {
        super.writeln("//" + line);
        return this;
    }

    public FilePen addPackage(String line) {
        super.writeln("package = \"" + line + "\";");
        return this;
    }

    public FilePen setJavaPackage(String line) {
        super.writeln("option java_package = \"" + line + "\";");
        return this;
    }

    public FilePen setOutterClassName(String line) {
        super.writeln("option java_outer_classname = \"" + line + "\";");
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

    public MessagePen messagePen(String name) {
        MessagePen mp = MessagePen.build(super.level, name);
        messages.add(mp);
        return mp;
    }

    public EnumPen enumPen(String name) {
        EnumPen ep = EnumPen.build(super.level, name);
        enums.add(ep);
        return ep;
    }

    public Iterator<MessagePen> messageIterator() {
        return messages.iterator();
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

    public final class FileDescriptor {

        public final String javaFQN;
        public final String protocFQN;

        public FileDescriptor(String javaFQN, String protocFQN) {
            this.javaFQN = javaFQN;
            this.protocFQN = protocFQN;
        }

    }
}
