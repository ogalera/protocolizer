//Protocolizer 29/04/2017 15:03:40
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.serializers;


public class prova2Serialized {
    public static class M1 {
        public byte[] dump(cat.ogasoft.examples.Test3.M1 target){
            return buildM1(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.prova2.M1 buildM1(cat.ogasoft.examples.Test3.M1 target){
            cat.ogasoft.protocolizer.messages.prova2.M1.Builder builder = cat.ogasoft.protocolizer.messages.prova2.M1.newBuilder();
            for(cat.ogasoft.examples.Test3.M2 k:target.getLlista()){
                builder.addLlista(cat.ogasoft.protocolizer.serializers.prova2Serialized.M2.buildM2(k));
            }
            return builder.build();
        }
    }

    public static class M2 {
        public byte[] dump(cat.ogasoft.examples.Test3.M2 target){
            return buildM2(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.prova2.M2 buildM2(cat.ogasoft.examples.Test3.M2 target){
            cat.ogasoft.protocolizer.messages.prova2.M2.Builder builder = cat.ogasoft.protocolizer.messages.prova2.M2.newBuilder();
            builder.setCaca(target.getCaca());
            return builder.build();
        }
    }

}
