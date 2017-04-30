//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class provaDeserializer {
    public static class M1 {
        public cat.ogasoft.examples.Test2.M1 dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.prova.M1 result = cat.ogasoft.protocolizer.messages.prova.M1.parseFrom(target);
            return buildM1(result);
        }
        public static cat.ogasoft.examples.Test2.M1 buildM1(cat.ogasoft.protocolizer.messages.prova.M1 target){
            cat.ogasoft.examples.Test2.M1 result = new cat.ogasoft.examples.Test2.M1();
            {
                java.util.ArrayList<Integer> r = new java.util.ArrayList<>(target.getLlistaCount());
                for(Integer k:target.getLlistaList()){
                    r.add(k);
                }
                result.setLlista(r);
            }
            return result;
        }
    }

}
