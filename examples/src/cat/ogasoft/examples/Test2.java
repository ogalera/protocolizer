/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.Collection;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 27, 2017 [9:43:09 PM]
 *
 * @brief DESCRIPTION
 */
//@ProtoFileV2.File(pJavaName = "prova", generateSource = true)
//@ProtoFileV2.Serialize
public class Test2 {
    
    @ProtoFileV2.File.Message
    public static class M1{
        @ProtoFileV2.File.Message.Field(comment = "prova", label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Integer> llista;

        public Collection<Integer> getLlista() {
            return llista;
        }

        public void setLlista(List<Integer> llista) {
            this.llista = llista;
        }
    }
    
}
