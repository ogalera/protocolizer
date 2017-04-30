package cat.ogasoft.protocolizer.deserializer.proves;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cat.ogasoft.protocolizer.deserializer;
//
//import com.google.protobuf.InvalidProtocolBufferException;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 30, 2017 [11:32:36 AM]
// *
// * @brief DESCRIPTION
// */
//public class AltaPedidosDeserialized {
//
//    public static class AltaMaestroPedido {
//
//        public cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido dump(byte[] target) throws InvalidProtocolBufferException {
//            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido amp = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido.parseFrom(target);
//            cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido result = new cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido();
//            result.setAnulaPendientes(amp.getAnulaPendientes());
//            
//            return result;
//        }
//    }
//}
