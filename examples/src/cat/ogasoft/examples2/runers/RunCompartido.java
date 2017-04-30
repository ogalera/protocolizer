/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples2.runers;

import cat.ogasoft.examples2.Compartido;
import cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer;
import cat.ogasoft.protocolizer.dumppers.serializers.CompartidoSerializer;
import java.util.Arrays;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 29, 2017 [11:54:12 PM]
 *
 * @brief DESCRIPTION
 */
public class RunCompartido {

    public static void main(String... args) throws Exception {
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
//        byte[] bytes = CompartidoSerializer.DatosTaller.buildDatosTaller(taller).toByteArray();
//        System.out.println(Arrays.toString(bytes));
//        System.out.println(CompartidoSerializer.DatosTaller.buildDatosTaller(taller).toByteArray().length);
//        Compartido.DatosTaller resposta = new CompartidoDeserializer.DatosTaller().dump(bytes);
//        System.out.println(resposta);

        Compartido.Archivo archivo = new Compartido.Archivo();
        archivo.setIdArchivo("asdfasdf");
        archivo.setTipo(Compartido.TipoArchivo.ALBARAN);
        byte[] bytes2 = new CompartidoSerializer.Archivo().dump(archivo);
        System.out.println(Arrays.toString(bytes2));
        System.out.println(new CompartidoDeserializer.Archivo().dump(bytes2));
    }
}
