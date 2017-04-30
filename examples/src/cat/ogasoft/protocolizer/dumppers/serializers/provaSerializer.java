//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.serializers;


public class provaSerializer {
    public static class M1 {
        public byte[] dump(cat.ogasoft.examples.Test2.M1 target){
            return buildM1(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.prova.M1 buildM1(cat.ogasoft.examples.Test2.M1 target){
            cat.ogasoft.protocolizer.messages.prova.M1.Builder builder = cat.ogasoft.protocolizer.messages.prova.M1.newBuilder();
            if(target.getLlista() != null){
                for(Integer k:target.getLlista()){
                    builder.addLlista(k);
                }
            }
            return builder.build();
        }
    }

}
