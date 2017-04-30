//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.serializers;


public class prova6Serializer {
    public static class M1 {
        public byte[] dump(cat.ogasoft.examples.Test4.M1 target){
            return buildM1(target).toByteArray();
        }
        public static class M2 {
            public byte[] dump(cat.ogasoft.examples.Test4.M1.M2 target){
                return buildM2(target).toByteArray();
            }
            public static cat.ogasoft.protocolizer.messages.prova6.M1.M2 buildM2(cat.ogasoft.examples.Test4.M1.M2 target){
                cat.ogasoft.protocolizer.messages.prova6.M1.M2.Builder builder = cat.ogasoft.protocolizer.messages.prova6.M1.M2.newBuilder();
                builder.setC(target.getC());
                return builder.build();
            }
        }
        public static cat.ogasoft.protocolizer.messages.prova6.M1 buildM1(cat.ogasoft.examples.Test4.M1 target){
            cat.ogasoft.protocolizer.messages.prova6.M1.Builder builder = cat.ogasoft.protocolizer.messages.prova6.M1.newBuilder();
            if(target.getM1() != null){
                builder.setM1(cat.ogasoft.protocolizer.dumppers.serializers.prova6Serializer.M1.M2.buildM2(target.getM1()));
            }
            return builder.build();
        }
    }

}
