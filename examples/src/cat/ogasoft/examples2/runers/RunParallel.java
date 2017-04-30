//package cat.ogasoft.examples2.runers;
//
//import cat.ogasoft.examples2.Compartido;
//import cat.ogasoft.protocolizer.serializers.CompartidoSerialized;
//import java.util.Arrays;
//
///**
// * @author Oscar Galera i Alfaro
// * @date   Apr 30, 2017 [10:33:52 AM]
// *
// * @brief DESCRIPTION
// */
//public class RunParallel {
//
//    public static void main(String... args) throws InterruptedException {
//        Compartido.Referencia ref = new Compartido.Referencia();
//        ref.setReferencia("asdfasdf");
//        ref.setTipo(Compartido.TipoReferencia.OEM);
////        CompartidoSerialized.Referencia sref = new CompartidoSerialized.Referencia();
////        sref.work(ref);
////        byte[] bytes = sref.waitUntilEnd();
////        System.out.println(Arrays.toString(bytes));
//        System.out.println(Arrays.toString(CompartidoSerialized.Referencia.buildReferencia(ref).toByteArray()));
//    }
//}
