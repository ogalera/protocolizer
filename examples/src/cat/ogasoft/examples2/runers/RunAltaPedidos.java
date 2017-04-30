///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cat.ogasoft.examples2.runers;
//
//import cat.ogasoft.examples2.AltaPedidos;
//import cat.ogasoft.examples2.Compartido;
//import cat.ogasoft.protocolizer.serializers.AltaPedidosSerialized;
//import java.util.Arrays;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 30, 2017 [12:14:34 AM]
// *
// * @brief DESCRIPTION
// */
//public class RunAltaPedidos {
//
//    public static void main(String... args) {
//        AltaPedidos.AltaDetallePedido adp = new AltaPedidos.AltaDetallePedido();
//        Compartido.Identificacion iden = new Compartido.Identificacion();
//        adp.setArticulo(iden);
//        adp.setUnidades(1000);
//        System.out.println(Arrays.toString(AltaPedidosSerialized.AltaDetallePedido.buildAltaDetallePedido(adp).toByteArray()));
//    }
//}
