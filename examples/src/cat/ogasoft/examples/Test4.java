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
 * @date   Apr 27, 2017 [10:44:52 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "prova6", generateSource = true)
@ProtoFileV2.Dumpper
public class Test4 {

    @ProtoFileV2.File.Message
    public static class M1 {

        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private M2 m1;

        public M2 getM1() {
            return m1;
        }

        public void setM1(M2 m1) {
            this.m1 = m1;
        }

        @ProtoFileV2.File.Message
        public static class M2 {

            @ProtoFileV2.File.Message.Field
            private int c;

            public int getC() {
                return c;
            }

            public void setC(int c) {
                this.c = c;
            }

        }

    }
}
