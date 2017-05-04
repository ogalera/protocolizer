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
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 29, 2017 [3:13:24 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova7", generateSource = true)
//@ProtoFileV2.Dumpper
//@ProtoFileV2.File.Import(importClass = Test4.class)
public class Test5 {

    @ProtoFileV2.File.Message
    public static class C1 {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Test4.M1 aaa;

        public Test4.M1 getAaa() {
            return aaa;
        }

        public void setAaa(Test4.M1 aaa) {
            this.aaa = aaa;
        }
        
        @ProtoFileV2.File.Message.Field
        private int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

    }
}
