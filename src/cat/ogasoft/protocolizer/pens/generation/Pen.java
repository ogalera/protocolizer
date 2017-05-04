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
 * @brief Generic pen for write java source.
 */
public class Pen {

    public final int level; //<Nested level.
    private final String tab; //<Tab for this level.
    private final String innerTab; //<Tab for next level.
    private final String innerInnerTab; //<Tab for innerTab next level.
    private final String innerInnerInnerTab; //<Tab for innerInnerTab next level.
    private final String innerInnerInnerInnerTab; //<Tab for innerInnerInnerTab next level
    private final List<String> lines; //<Java lines.
    private String header; //<Source header.
    private String footer;//<Source footer

    /**
     * @pre level >= 0
     * @post generates a java pen based on parameters.
     * @param level nested for source.
     * @param header source.
     * @param footer source.
     */
    public Pen(int level, String header, String footer) {
        this(level);
        this.header = header;
        this.footer = footer;
    }

    /**
     * @pre level >= 0
     * @post generates a java pen without header and footer
     * @param level nested for source.
     */
    public Pen(int level) {
        this.level = level;
        tab = tabs(level);
        innerTab = "    " + tab;
        innerInnerTab = "    " + innerTab;
        innerInnerInnerTab = "    " + innerInnerTab;
        innerInnerInnerInnerTab = "    " + innerInnerInnerTab;
        this.lines = new LinkedList<>();
    }

    /**
     * @pre level >= 0
     * @post returns a string thats represents nested level.
     */
    private String tabs(int level) {
        StringBuilder tabs = new StringBuilder();
        for (int i = 0; i < level; i++) {
            tabs.append("    ");
        }
        return tabs.toString();
    }

    /**
     * @pre --
     * @post writes a new line in next nested level and jumps
     */
    public void writeInnln(String line) {
        writeInn(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line in next nested level.
     */
    public void writeInn(String line) {
        lines.add(innerTab + line);
    }

    /**
     * @pre --
     * @post writes a new line in 2 next nested level and jumps.
     */
    public void writeInnInnTabln(String line) {
        writeInnInn(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line in 2 next nested level.
     */
    public void writeInnInn(String line) {
        lines.add(innerInnerTab + line);
    }

    /**
     * @pre --
     * @post writes a new line in 3 next nested level and jumps.
     */
    public void writeInnInnInnln(String line) {
        writeInnInnInn(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line in 3 next nested level.
     */
    public void writeInnInnInn(String line) {
        lines.add(innerInnerInnerTab + line);
    }

    /**
     * @pre --
     * @post writes a new line in 4 next nested level and jumps.
     */
    public void writeInnInnInnInnln(String line) {
        writeInnInnInnInn(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line in 4 next nested level.
     */
    public void writeInnInnInnInn(String line) {
        lines.add(innerInnerInnerInnerTab + line);
    }

    /**
     * @pre --
     * @post writes a new line and jumps.
     */
    public void writeln(String line) {
        write(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line.
     */
    public void write(String line) {
        lines.add(tab + line);
    }

    /**
     * @pre --
     * @post writes a new line without level tab and jumps.
     */
    public void writeNoTabln(String line) {
        writeNoTab(line);
        newLine();
    }

    /**
     * @pre --
     * @post writes a new line without level tab.
     */
    public void writeNoTab(String line) {
        lines.add(line);
    }

    /**
     * @pre --
     * @post jumps.
     */
    public void newLine() {
        lines.add("\n");
    }

    /**
     * @pre --
     * @post returns an iterator through lines (without header and footer).
     */
    public Iterator<String> iterator() {
        return lines.iterator();
    }

    /**
     * @pre target is not null
     * @post add the header to container.
     */
    public void addBegin(List<String> container) {
        container.add(tab + header);
        container.add("\n");
    }

    /**
     * @pre target is not null.
     * @post adds lines in container.
     */
    public void addContent(List<String> container) {
        container.addAll(lines);
    }

    /**
     * @pre target is not null
     * @post add the footer in container.
     */
    public void addEnd(List<String> container) {
        container.add(tab + footer);
        container.add("\n");
    }
}
