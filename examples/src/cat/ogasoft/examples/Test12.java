/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 30, 2017 [3:11:23 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova12", generateSource = true)
//@ProtoFileV2.Dumpper
public class Test12 {

    @ProtoFileV2.File.Enum
    public static enum Enumm {
        RES;
    }

    @ProtoFileV2.File.Message
    public static class Prova {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Enumm> c;

        public List<Enumm> getC() {
            return c;
        }

        public void setC(List<Enumm> c) {
            this.c = c;
        }

    }
//
//    @ProtoFileV2.File.Message
//    public static class Prova2 {
//
//        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
//        private List<Prova> prova;
//
//        public List<Prova> getProva() {
//            return prova;
//        }
//
//        public void setProva(List<Prova> prova) {
//            this.prova = prova;
//        }
//
//    }
}
