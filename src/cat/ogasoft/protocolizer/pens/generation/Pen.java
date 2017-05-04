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
    private final String innerInnerInnerTab;
    private final String innerInnerInnerInnerTab;
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
        innerInnerInnerTab = "    " + innerInnerTab;
        innerInnerInnerInnerTab = "    " + innerInnerInnerTab;
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

    public void writeInnInnInnTabln(String line) {
        writeInnInnInnln(line);
        newLine();
    }

    public void writeInnInnInnln(String line) {
        lines.add(innerInnerInnerTab + line);
    }

    public void writeInnInnInnInnTabln(String line) {
        writeInnInnInnInnln(line);
        newLine();
    }

    public void writeInnInnInnInnln(String line) {
        lines.add(innerInnerInnerInnerTab + line);
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
