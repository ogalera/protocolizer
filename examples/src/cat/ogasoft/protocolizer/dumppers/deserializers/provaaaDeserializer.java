//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class provaaaDeserializer {
    public static class M1 {
        public cat.ogasoft.examples.Test1.M1 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.provaaa.M1 result = cat.ogasoft.protocolizer.messages.provaaa.M1.parseFrom(target);
            return buildM1(result);
        }
        public static cat.ogasoft.examples.Test1.M1 buildM1(cat.ogasoft.protocolizer.messages.provaaa.M1 target){
            cat.ogasoft.examples.Test1.M1 result = new cat.ogasoft.examples.Test1.M1();
            if(target.hasM3()){
                result.setM3(target.getM3());
            }
            return result;
        }
    }

    public static class M3 {
        public cat.ogasoft.examples.Test1.M3 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.provaaa.M3 result = cat.ogasoft.protocolizer.messages.provaaa.M3.parseFrom(target);
            return buildM3(result);
        }
        public static cat.ogasoft.examples.Test1.M3 buildM3(cat.ogasoft.protocolizer.messages.provaaa.M3 target){
            cat.ogasoft.examples.Test1.M3 result = new cat.ogasoft.examples.Test1.M3();
            result.setCaca(target.getCaca());
            if(target.hasProva()){
                result.setProva(cat.ogasoft.protocolizer.dumppers.deserializers.provaaaDeserializer.M2.buildM2(target.getProva()));
            }
            return result;
        }
    }

    public static class M2 {
        public cat.ogasoft.examples.Test1.M2 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.provaaa.M2 result = cat.ogasoft.protocolizer.messages.provaaa.M2.parseFrom(target);
            return buildM2(result);
        }
        public static cat.ogasoft.examples.Test1.M2 buildM2(cat.ogasoft.protocolizer.messages.provaaa.M2 target){
            cat.ogasoft.examples.Test1.M2 result = new cat.ogasoft.examples.Test1.M2();
            if(target.hasM1()){
                result.setM1(cat.ogasoft.protocolizer.dumppers.deserializers.provaaaDeserializer.M1.buildM1(target.getM1()));
            }
            if(target.hasM3()){
                result.setM3(cat.ogasoft.protocolizer.dumppers.deserializers.provaaaDeserializer.M3.buildM3(target.getM3()));
            }
            if(target.hasOk()){
                result.setM2(cat.ogasoft.protocolizer.dumppers.deserializers.provaaaDeserializer.M3.buildM3(target.getOk()));
            }
            return result;
        }
    }

}
