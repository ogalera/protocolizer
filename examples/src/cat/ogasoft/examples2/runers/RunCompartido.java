///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package cat.ogasoft.examples2.runers;
//
//import cat.ogasoft.examples2.Compartido;
//import cat.ogasoft.protocolizer.serializers.CompartidoSerialized;
//import java.util.Arrays;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 29, 2017 [11:54:12 PM]
// *
// * @brief DESCRIPTION
// */
//public class RunCompartido {
//
//    public static void main(String... args) {
//        Compartido.Direccion direccion = new Compartido.Direccion();
//        direccion.setCodigoPostal("17834");
//        direccion.setNif("07264645-A");
//        direccion.setNumero("asdf64646");
//        direccion.setProvincia("Girona");
//        Compartido.DatosTaller taller = new Compartido.DatosTaller();
//        taller.setNombreComercial("Taller oscar galera");
//        taller.setDireccion(direccion);
//        taller.setMovil("649709239");
//        taller.setPrecioHora(6.8);
//        System.out.println(Arrays.toString(CompartidoSerialized.DatosTaller.buildDatosTaller(taller).toByteArray()));
//        System.out.println(CompartidoSerialized.DatosTaller.buildDatosTaller(taller).toByteArray().length);
//    }
//}
