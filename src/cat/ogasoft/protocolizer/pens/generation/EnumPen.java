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
 * @brief A pen to write a new Protocol Buffer enumesration.
 */
public class EnumPen extends Pen {

    private int ids; //<Identifier counter for each enum value;
    public final String mJavaPackage; //<Message Java package.
    public final String mJavaClass; //<Message Java name.
    public final String mJavaFQN; //<Message Java FQN (mJavaPackage.mJavaClass).
    public final String pJavaPackage; //<Protocol buffer message package.
    public final String pJavaClass;//<Protocol buffer message name.
    public final String pJavaFQN;//<Protocol buffer message FQN (pJavaPackage.pJavaClass).

    /**
     * @pre level >= 0
     * @post new EnumPen has been generated.
     * @param level for enumeration.
     * @param mJavaPackage enumeration java package.
     * @param mJavaClass enumeration java name.
     * @param pJavaPackage Protocol buffer enumeration package.
     * @param pJavaClass Protocol buffer enumeration message.
     */
    private EnumPen(int level,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass) {
        super(level, "enum " + pJavaClass + " {", "}");
        this.mJavaPackage = mJavaPackage;
        this.mJavaClass = mJavaClass;
        this.mJavaFQN = mJavaPackage + '.' + mJavaClass;
        this.pJavaPackage = pJavaPackage;
        this.pJavaClass = pJavaClass;
        this.pJavaFQN = pJavaPackage + '.' + pJavaClass;
        this.ids = 0;
    }

    /**
     * @pre level >= 0
     * @post new EnumPen has been generated.
     * @param level for enumeration.
     * @param mJavaPackage enumeration java package.
     * @param mJavaClass enumeration java name.
     * @param pJavaPackage Protocol buffer enumeration package.
     * @param pJavaClass Protocol buffer enumeration message.
     */
    public static EnumPen build(int level,
            String mJavaPackage,
            String mJavaClass,
            String pJavaPackage,
            String pJavaClass) {
        return new EnumPen(level, mJavaPackage, mJavaClass, pJavaPackage, pJavaClass);
    }

    /**
     * @pre --
     * @post new enumeration value has been added.
     */
    public EnumPen addValue(String value) {
        super.writeInnln(value + " = " + (ids++) + ";");
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
