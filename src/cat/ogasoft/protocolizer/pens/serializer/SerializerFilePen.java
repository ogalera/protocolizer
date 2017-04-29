package cat.ogasoft.protocolizer.pens.serializer;

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
public class SerializerFilePen extends Pen {

    private final File fileDump;
    private SerializerRootMessagePen messageRoot;
    public final FileDescriptor fileDescriptor;

    private SerializerFilePen(File fileDump, FileDescriptor fileDescriptor) {
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

    public SerializerFilePen addComment(String line) {
        super.writeln("//" + line);
        return this;
    }

    public static SerializerFilePen build(File fileDump,
            String sJavaPackage,
            String sJavaClass,
            FilePen.FileDescriptor descriptor) {
        return new SerializerFilePen(fileDump, new FileDescriptor(descriptor, sJavaPackage, sJavaClass, sJavaPackage + '.' + sJavaClass));
    }

    public SerializerRootMessagePen buildRoot(Map<String, String> builders) {
        this.messageRoot = SerializerRootMessagePen.build(fileDescriptor.sJavaClass, fileDescriptor.sJavaFQN, builders);
        return this.messageRoot;
    }

    public void constructMethods(Map<String, String> methodsNS, Map<String, String> enumsNS, Map<String, String> mFQN2pFQN) {
        messageRoot.constructMethods(methodsNS, enumsNS, mFQN2pFQN);
    }

    public void dump() throws IOException {
        dump(fileDump);
    }

    public void dump(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> fileLines = iterator();
            while (fileLines.hasNext()) {
                writer.write(fileLines.next());
            }
            Iterator<String> messageLines = messageRoot.iterator();
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
