///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cat.ogasoft.exemples.runers;
//
//import cat.ogasoft.examples.Test10;
//import cat.ogasoft.protocolizer.dumppers.deserializers.prova10Deserializer;
//import cat.ogasoft.protocolizer.dumppers.serializers.prova10Serializer;
//import java.util.Arrays;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 30, 2017 [3:56:42 PM]
// *
// * @brief DESCRIPTION
// */
//public class Run10 {
//
//    public static void main(String... args) throws Exception {
//        Test10.Prova msg = new Test10.Prova();
//        msg.setC(10);
//        System.out.println(msg);
//        byte[] bytes = prova10Serializer.Prova.buildProva(msg).toByteArray();
//        System.out.println("bytes");
//        System.out.println(Arrays.toString(bytes));
//        System.out.println("missatge");
//        System.out.println(new prova10Deserializer.Prova().dump(bytes));
//    }
//}
