//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class prova6Deserializer {
    public static class M1 {
        public cat.ogasoft.examples.Test4.M1 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.prova6.M1 result = cat.ogasoft.protocolizer.messages.prova6.M1.parseFrom(target);
            return buildM1(result);
        }
        public static class M2 {
            public cat.ogasoft.examples.Test4.M1.M2 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
                cat.ogasoft.protocolizer.messages.prova6.M1.M2 result = cat.ogasoft.protocolizer.messages.prova6.M1.M2.parseFrom(target);
                return buildM2(result);
            }
            public static cat.ogasoft.examples.Test4.M1.M2 buildM2(cat.ogasoft.protocolizer.messages.prova6.M1.M2 target){
                cat.ogasoft.examples.Test4.M1.M2 result = new cat.ogasoft.examples.Test4.M1.M2();
                result.setC(target.getC());
                return result;
            }
        }
        public static cat.ogasoft.examples.Test4.M1 buildM1(cat.ogasoft.protocolizer.messages.prova6.M1 target){
            cat.ogasoft.examples.Test4.M1 result = new cat.ogasoft.examples.Test4.M1();
            if(target.hasM1()){
                result.setM1(cat.ogasoft.protocolizer.dumppers.deserializers.prova6Deserializer.M1.M2.buildM2(target.getM1()));
            }
            return result;
        }
    }

}
