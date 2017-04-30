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
 * @date Apr 27, 2017 [10:06:45 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova2", generateSource = true)
//@ProtoFileV2.Serialize
public class Test3 {

    @ProtoFileV2.File.Message
    public static class M1 {

        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<M2> llista;

        public List<M2> getLlista() {
            return llista;
        }

        public void setLlista(List<M2> llista) {
            this.llista = llista;
        }
    }

    @ProtoFileV2.File.Message
    public static class M2 {

        @ProtoFileV2.File.Message.Field(comment = "prova")
        private int caca;

        public int getCaca() {
            return caca;
        }

        public void setCaca(int caca) {
            this.caca = caca;
        }
    }
}
