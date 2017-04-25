//Protocolizer 24/04/2017 22:12:04
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.serializers;


public class FileSerialized {
    public static class SearchRequest {
        public byte[] dump(cat.ogasoft.protocolizer.test.Test2.SearchRequest target){
            return m1(target).toByteArray();
        }
        
        public static cat.ogasoft.protocolizer.messages.File.SearchRequest m1(cat.ogasoft.protocolizer.test.Test2.SearchRequest target){
            cat.ogasoft.protocolizer.messages.File.SearchRequest.Builder builder = cat.ogasoft.protocolizer.messages.File.SearchRequest.newBuilder();
            
            return builder.build();
        }
    }
}
