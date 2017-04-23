package cat.ogasoft.protocolizer.pens;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 20, 2017 [9:50:18 PM]
 *
 * @brief DESCRIPTION
 */
public class EnumPen extends Pen {

    private int ids; //<Identifier for each enum value;

    private EnumPen(int level, String name) {
        super(level, "enum " + name + " {", "}");
        this.ids = 0;
    }

    public static EnumPen build(int level, String name) {
        return new EnumPen(level, name);
    }

    public EnumPen addValue(String value) {
        super.writeInnTabln(value + " = " + (ids++) +";");
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        List<String> content = new LinkedList<>();
        super.addBegin(content);
        super.addContent(content);
        super.addEnd(content);
        return content.iterator();
    }
}
