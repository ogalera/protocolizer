package cat.ogasoft.protocolizer.pens;

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
    private final String tabs;
    private final String innerTabs;
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
        tabs = tabs(level);
        innerTabs = "    " + tabs;
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
        lines.add(innerTabs + line);
    }

    public void writeTabln(String line) {
        writeTab(line);
        newLine();
    }

    public void writeTab(String line) {
        lines.add(tabs + line);
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
        target.add(tabs + begin);
        target.add("\n");
    }

    public void addContent(List<String> target) {
        target.addAll(lines);
    }

    public void addEnd(List<String> target) {
        target.add(tabs + end);
        target.add("\n");
    }
}
