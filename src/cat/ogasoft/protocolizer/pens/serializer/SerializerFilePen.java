package cat.ogasoft.protocolizer.pens.serializer;

import cat.ogasoft.protocolizer.pens.Pen;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 23, 2017 [5:36:42 PM]
 *
 * @brief DESCRIPTION
 */
public class SerializerFilePen extends Pen {

    private SerializerMessagePen message;

    private SerializerFilePen(String packagee) {
        super(0);
        addComment("Protocolizer " + new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(new Date()));
        addComment("This class has been generated automatically, plase");
        addComment("DO NOT EDIT!");
        addComment("");
        addComment("For any question, feel free to contact me at: oscar.galeraa@gmail.com");
        super.newLine();
        super.writeln("package " + packagee + ";");
        super.newLine();
        super.newLine();
    }

    public SerializerFilePen addComment(String line) {
        super.writeln("//" + line);
        return this;
    }

    public static SerializerFilePen build(String packagee) {
        return new SerializerFilePen(packagee);
    }

    public SerializerMessagePen buildPublicMessage(String name) {
        message = SerializerMessagePen.buildPublic(0, name);
        return message;
    }

    public void dump(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Iterator<String> fileLines = super.iterator();
            while (fileLines.hasNext()) {
                writer.write(fileLines.next());
            }
            Iterator<String> messageLines = message.iterator();
            while (messageLines.hasNext()) {
                writer.write(messageLines.next());
            }
        }
    }
}
