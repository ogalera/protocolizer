package cat.ogasoft.protocolizer.pens.generation;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 20, 2017 [8:58:10 PM]
 *
 * @brief Generic pen
 */
public class Pen {

    public final int level;
    private final String tab;
    private final String innerTab;
    private final String innerInnerTab;
    private final List<String> lines;
    private String begin;
    private String end;

    public Pen(int level, String begin, String end) {
        this(level);
        this.begin = begin;
        this.end = end;
    }

    public Pen(int level) {
        this.level = level;
        tab = tabs(level);
        innerTab = "    " + tab;
        innerInnerTab = "    " + innerTab;
        this.lines = new LinkedList<>();
    }

    private String tabs(int level) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < level; i++) {
            tabs.append("    ");
        }
        return tabs.toString();
    }

    public void writeInnTabln(String line) {
        writeInnln(line);
        newLine();
    }

    public void writeInnln(String line) {
        lines.add(innerTab + line);
    }

    public void writeInnInnTabln(String line) {
        writeInnInnln(line);
        newLine();
    }

    public void writeInnInnln(String line) {
        lines.add(innerInnerTab + line);
    }

    public void writeTabln(String line) {
        writeTab(line);
        newLine();
    }

    public void writeTab(String line) {
        lines.add(tab + line);
    }

    public void writeln(String line) {
        write(line);
        newLine();
    }

    public void write(String line) {
        lines.add(line);
    }

    public void newLine() {
        lines.add("\n");
    }

    public Iterator<String> iterator() {
        return lines.iterator();
    }

    public void addBegin(List<String> target) {
        target.add(tab + begin);
        target.add("\n");
    }

    public void addContent(List<String> target) {
        target.addAll(lines);
    }

    public void addEnd(List<String> target) {
        target.add(tab + end);
        target.add("\n");
    }
}
