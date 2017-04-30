///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cat.ogasoft.exemples.runers;
//
//import cat.ogasoft.examples.Test11;
//import cat.ogasoft.protocolizer.dumppers.deserializers.prova11Deserializer;
//import cat.ogasoft.protocolizer.dumppers.serializers.prova11Serializer;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 30, 2017 [3:56:42 PM]
// *
// * @brief DESCRIPTION
// */
//public class Run11 {
//
//    public static void main(String... args) throws Exception {
//        Test11.Prova msg = new Test11.Prova();
////        List<Integer> list = new LinkedList<>();
//////        list.add(0);
//////        list.add(1);
//////        list.add(2);
//////        list.add(3);
////        msg.setC(list);
//        System.out.println(msg);
//        byte[] bytes = prova11Serializer.Prova.buildProva(msg).toByteArray();
//        System.out.println("bytes");
//        System.out.println(Arrays.toString(bytes));
//        System.out.println("missatge");
//        System.out.println(new prova11Deserializer.Prova().dump(bytes));
//    }
//}
