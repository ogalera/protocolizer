/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
