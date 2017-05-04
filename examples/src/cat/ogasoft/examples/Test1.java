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
 * @date Apr 25, 2017 [10:08:07 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "provaaa", generateSource = true)
@ProtoFileV2.Dumpper
public class Test1 {

    @ProtoFileV2.File.Message
    public static class M1 {
        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Integer m3;

        public Integer getM3() {
            return m3;
        }

        public void setM3(Integer m3) {
            this.m3 = m3;
        }

    }

    @ProtoFileV2.File.Message
    public static class M3 {

        @ProtoFileV2.File.Message.Field(comment = "prova")
        private int caca;
        
        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private M2 prova;

        public M2 getProva() {
            return prova;
        }

        public void setProva(M2 prova) {
            this.prova = prova;
        }

        public int getCaca() {
            return caca;
        }

        public void setCaca(int caca) {
            this.caca = caca;
        }
        
        
    }

    @ProtoFileV2.File.Message
    public static class M2 {

        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private M1 m1;

        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private M3 m3;

        @ProtoFileV2.File.Message.Field(name="ok", comment = "prova", label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private M3 m2;

        public M1 getM1() {
            return m1;
        }

        public void setM1(M1 m1) {
            this.m1 = m1;
        }

        public M3 getM2() {
            return m2;
        }

        public void setM2(M3 m2) {
            this.m2 = m2;
        }

        public M3 getM3() {
            return m3;
        }

        public void setM3(M3 m3) {
            this.m3 = m3;
        }

    }
    
}
