//Protocolizer 29/04/2017 15:03:40
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.serializers;


public class provaaaSerialized {
    public static class M1 {
        public byte[] dump(cat.ogasoft.examples.Test1.M1 target){
            return buildM1(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.provaaa.M1 buildM1(cat.ogasoft.examples.Test1.M1 target){
            cat.ogasoft.protocolizer.messages.provaaa.M1.Builder builder = cat.ogasoft.protocolizer.messages.provaaa.M1.newBuilder();
            if(target.getM3() != 0){
                builder.setM3(target.getM3());
            }
            return builder.build();
        }
    }

    public static class M3 {
        public byte[] dump(cat.ogasoft.examples.Test1.M3 target){
            return buildM3(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.provaaa.M3 buildM3(cat.ogasoft.examples.Test1.M3 target){
            cat.ogasoft.protocolizer.messages.provaaa.M3.Builder builder = cat.ogasoft.protocolizer.messages.provaaa.M3.newBuilder();
            builder.setCaca(target.getCaca());
            if(target.getProva() != null){
                builder.setProva(cat.ogasoft.protocolizer.serializers.provaaaSerialized.M2.buildM2(target.getProva()));
            }
            return builder.build();
        }
    }

    public static class M2 {
        public byte[] dump(cat.ogasoft.examples.Test1.M2 target){
            return buildM2(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.provaaa.M2 buildM2(cat.ogasoft.examples.Test1.M2 target){
            cat.ogasoft.protocolizer.messages.provaaa.M2.Builder builder = cat.ogasoft.protocolizer.messages.provaaa.M2.newBuilder();
            if(target.getM1() != null){
                builder.setM1(cat.ogasoft.protocolizer.serializers.provaaaSerialized.M1.buildM1(target.getM1()));
            }
            if(target.getM3() != null){
                builder.setM3(cat.ogasoft.protocolizer.serializers.provaaaSerialized.M3.buildM3(target.getM3()));
            }
            if(target.getM2() != null){
                builder.setOk(cat.ogasoft.protocolizer.serializers.provaaaSerialized.M3.buildM3(target.getM2()));
            }
            return builder.build();
        }
    }

}
