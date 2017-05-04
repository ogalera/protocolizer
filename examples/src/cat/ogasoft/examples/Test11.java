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
import java.util.Arrays;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 30, 2017 [3:11:23 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova11", generateSource = true)
//@ProtoFileV2.Dumpper
public class Test11 {

    @ProtoFileV2.File.Message
    public static class Prova {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Integer> c;

        public List<Integer> getC() {
            return c;
        }

        public void setC(List<Integer> c) {
            this.c = c;
        }

        @Override
        public String toString() {
            if (c != null) {
                return "Prova{" + "c=" + Arrays.toString(c.toArray()) + '}';
            }
            return "Null";
        }

    }
}
