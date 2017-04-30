//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.serializers;


public class prova7Serializer {
    public static class C1 {
        public byte[] dump(cat.ogasoft.examples.Test5.C1 target){
            return buildC1(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.prova7.C1 buildC1(cat.ogasoft.examples.Test5.C1 target){
            cat.ogasoft.protocolizer.messages.prova7.C1.Builder builder = cat.ogasoft.protocolizer.messages.prova7.C1.newBuilder();
            if(target.getAaa() != null){
                builder.setAaa(cat.ogasoft.protocolizer.dumppers.serializers.prova6Serializer.M1.buildM1(target.getAaa()));
            }
            builder.setA(target.getA());
            return builder.build();
        }
    }

}
