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
 * @date Apr 20, 2017 [9:50:18 PM]
 *
 * @brief DESCRIPTION
 */
public class EnumPen extends Pen {

    private int ids; //<Identifier for each enum value;
    public final String mJavaPackage;
    public final String mJavaClass;
    public final String mJavaFQN;
    public final String pJavaPackage;
    public final String pJavaClass;
    public final String pJavaFQN;

    private EnumPen(int level, String mJavaPackage, String mJavaClass, String pJavaPackage, String pJavaClass) {
        super(level, "enum " + pJavaClass + " {", "}");
        this.mJavaPackage = mJavaPackage;
        this.mJavaClass = mJavaClass;
        this.mJavaFQN = mJavaPackage + '.' + mJavaClass;
        this.pJavaPackage = pJavaPackage;
        this.pJavaClass = pJavaClass;
        this.pJavaFQN = pJavaPackage + '.' + pJavaClass;
        this.ids = 0;
    }

    public static EnumPen build(int level, String mJavaPackage, String mJavaClass, String pJavaPackage, String pJavaClass) {
        return new EnumPen(level, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass);
    }
    
    public EnumPen addValue(String value) {
        super.writeInnTabln(value + " = " + (ids++) + ";");
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
