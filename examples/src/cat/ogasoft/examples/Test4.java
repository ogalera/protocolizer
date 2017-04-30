/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 27, 2017 [10:44:52 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova6", generateSource = true)
//@ProtoFileV2.Serialize
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
