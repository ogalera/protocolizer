//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class prova7Deserializer {
    public static class C1 {
        public cat.ogasoft.examples.Test5.C1 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.prova7.C1 result = cat.ogasoft.protocolizer.messages.prova7.C1.parseFrom(target);
            return buildC1(result);
        }
        public static cat.ogasoft.examples.Test5.C1 buildC1(cat.ogasoft.protocolizer.messages.prova7.C1 target){
            cat.ogasoft.examples.Test5.C1 result = new cat.ogasoft.examples.Test5.C1();
            if(target.hasAaa()){
                result.setAaa(cat.ogasoft.protocolizer.dumppers.deserializers.prova6Deserializer.M1.buildM1(target.getAaa()));
            }
            result.setA(target.getA());
            return result;
        }
    }

}
