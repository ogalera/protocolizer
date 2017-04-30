//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com
package cat.ogasoft.protocolizer.dumppers.deserializers;

public class CompartidoDeserializer {

    public static class Referencia extends Thread implements cat.ogasoft.protocolizer.DeserializerProtoWorker<cat.ogasoft.examples2.Compartido.Referencia> {

        private byte[] bytes;
        private cat.ogasoft.examples2.Compartido.Referencia container;
        private String errMsg;

        @Override
        public void work(byte[] bytes) {
            super.setName("cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.ReferenciaWorker");
            this.bytes = bytes;
            start();
        }

        @Override
        public cat.ogasoft.examples2.Compartido.Referencia waitUntilEnd() throws InterruptedException, cat.ogasoft.protocolizer.exceptions.DeserializationException {
            this.join();
            if (container == null) {
                throw new cat.ogasoft.protocolizer.exceptions.DeserializationException("Deserialization exception, message [" + errMsg + "]");
            }
            return container;
        }

        @Override
        public void run() {
            try {
                container = dump(bytes);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                errMsg = e.getMessage();
            }
        }

        public cat.ogasoft.examples2.Compartido.Referencia dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Referencia result = cat.ogasoft.protocolizer.messages.Compartido.Referencia.parseFrom(target);
            return buildReferencia(result);
        }

        public static cat.ogasoft.examples2.Compartido.Referencia buildReferencia(cat.ogasoft.protocolizer.messages.Compartido.Referencia target) {
            cat.ogasoft.examples2.Compartido.Referencia result = new cat.ogasoft.examples2.Compartido.Referencia();
            result.setReferencia(target.getReferencia());
            result.setTipo(cat.ogasoft.examples2.Compartido.TipoReferencia.valueOf(target.getTipo().name()));
            return result;
        }
    }

    public static class Marca {

        public cat.ogasoft.examples2.Compartido.Marca dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Marca result = cat.ogasoft.protocolizer.messages.Compartido.Marca.parseFrom(target);
            return buildMarca(result);
        }

        public static cat.ogasoft.examples2.Compartido.Marca buildMarca(cat.ogasoft.protocolizer.messages.Compartido.Marca target) {
            cat.ogasoft.examples2.Compartido.Marca result = new cat.ogasoft.examples2.Compartido.Marca();
            result.setMarca(target.getMarca());
            result.setTipo(cat.ogasoft.examples2.Compartido.TipoMarca.valueOf(target.getTipo().name()));
            if (target.hasNombre()) {
                result.setNombre(target.getNombre());
            }
            return result;
        }
    }

    public static class Identificacion {

        public cat.ogasoft.examples2.Compartido.Identificacion dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Identificacion result = cat.ogasoft.protocolizer.messages.Compartido.Identificacion.parseFrom(target);
            return buildIdentificacion(result);
        }

        public static cat.ogasoft.examples2.Compartido.Identificacion buildIdentificacion(cat.ogasoft.protocolizer.messages.Compartido.Identificacion target) {
            cat.ogasoft.examples2.Compartido.Identificacion result = new cat.ogasoft.examples2.Compartido.Identificacion();
            if (target.hasReferencia()) {
                result.setReferencia(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Referencia.buildReferencia(target.getReferencia()));
            }
            if (target.hasMarca()) {
                result.setMarca(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Marca.buildMarca(target.getMarca()));
            }
            if (target.getOtrasList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Referencia> r = new java.util.ArrayList<>(target.getOtrasCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Referencia k : target.getOtrasList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Referencia.buildReferencia(k));
                }
                result.setOtras(r);
            }
            if (target.hasArticuloId()) {
                result.setArticuloId(target.getArticuloId());
            }
            if (target.getInformacionAdicionalList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Amplia> r = new java.util.ArrayList<>(target.getInformacionAdicionalCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Amplia k : target.getInformacionAdicionalList()) {
                    r.add(cat.ogasoft.examples2.Compartido.Amplia.valueOf(k.name()));
                }
                result.setInformacionAdicional(r);
            }
            return result;
        }
    }

    public static class Precio {

        public cat.ogasoft.examples2.Compartido.Precio dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Precio result = cat.ogasoft.protocolizer.messages.Compartido.Precio.parseFrom(target);
            return buildPrecio(result);
        }

        public static cat.ogasoft.examples2.Compartido.Precio buildPrecio(cat.ogasoft.protocolizer.messages.Compartido.Precio target) {
            cat.ogasoft.examples2.Compartido.Precio result = new cat.ogasoft.examples2.Compartido.Precio();
            if (target.hasPrecio()) {
                result.setPrecio(target.getPrecio());
            }
            if (target.hasDescuento()) {
                result.setDescuento(target.getDescuento());
            }
            result.setTipo(cat.ogasoft.examples2.Compartido.TipoPrecio.valueOf(target.getTipo().name()));
            return result;
        }
    }

    public static class Promocion {

        public cat.ogasoft.examples2.Compartido.Promocion dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Promocion result = cat.ogasoft.protocolizer.messages.Compartido.Promocion.parseFrom(target);
            return buildPromocion(result);
        }

        public static cat.ogasoft.examples2.Compartido.Promocion buildPromocion(cat.ogasoft.protocolizer.messages.Compartido.Promocion target) {
            cat.ogasoft.examples2.Compartido.Promocion result = new cat.ogasoft.examples2.Compartido.Promocion();
            result.setEnVenta(target.getEnVenta());
            if (target.hasTitulo()) {
                result.setTitulo(target.getTitulo());
            }
            if (target.hasDescripcion()) {
                result.setDescripcion(target.getDescripcion());
            }
            if (target.hasImagenId()) {
                result.setImagenId(target.getImagenId());
            }
            return result;
        }
    }

    public static class Oferta {

        public cat.ogasoft.examples2.Compartido.Oferta dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Oferta result = cat.ogasoft.protocolizer.messages.Compartido.Oferta.parseFrom(target);
            return buildOferta(result);
        }

        public static cat.ogasoft.examples2.Compartido.Oferta buildOferta(cat.ogasoft.protocolizer.messages.Compartido.Oferta target) {
            cat.ogasoft.examples2.Compartido.Oferta result = new cat.ogasoft.examples2.Compartido.Oferta();
            result.setAplicacion(cat.ogasoft.examples2.Compartido.AplicacionOferta.valueOf(target.getAplicacion().name()));
            result.setVisible(cat.ogasoft.examples2.Compartido.VisualizacionOferta.valueOf(target.getVisible().name()));
            if (target.hasValorAplicacion()) {
                result.setValorAplicacion(target.getValorAplicacion());
            }
            if (target.hasDescripcion()) {
                result.setDescripcion(target.getDescripcion());
            }
            if (target.hasModificarPVP()) {
                result.setModificarPVP(target.getModificarPVP());
            }
            if (target.hasModificarDTO()) {
                result.setModificarDTO(target.getModificarDTO());
            }
            if (target.hasArticuloRegalo()) {
                result.setArticuloRegalo(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Articulo.buildArticulo(target.getArticuloRegalo()));
            }
            if (target.hasCantidadRegalo()) {
                result.setCantidadRegalo(target.getCantidadRegalo());
            }
            if (target.hasIdAnuncioEnlazar()) {
                result.setIdAnuncioEnlazar(target.getIdAnuncioEnlazar());
            }
            return result;
        }
    }

    public static class Stock {

        public cat.ogasoft.examples2.Compartido.Stock dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Stock result = cat.ogasoft.protocolizer.messages.Compartido.Stock.parseFrom(target);
            return buildStock(result);
        }

        public static cat.ogasoft.examples2.Compartido.Stock buildStock(cat.ogasoft.protocolizer.messages.Compartido.Stock target) {
            cat.ogasoft.examples2.Compartido.Stock result = new cat.ogasoft.examples2.Compartido.Stock();
            if (target.hasSucursalId()) {
                result.setSucursalId(target.getSucursalId());
            }
            if (target.hasUbicacion()) {
                result.setUbicacion(target.getUbicacion());
            }
            result.setStock(target.getStock());
            result.setStockMostrar(target.getStockMostrar());
            return result;
        }
    }

    public static class Tasa {

        public cat.ogasoft.examples2.Compartido.Tasa dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Tasa result = cat.ogasoft.protocolizer.messages.Compartido.Tasa.parseFrom(target);
            return buildTasa(result);
        }

        public static cat.ogasoft.examples2.Compartido.Tasa buildTasa(cat.ogasoft.protocolizer.messages.Compartido.Tasa target) {
            cat.ogasoft.examples2.Compartido.Tasa result = new cat.ogasoft.examples2.Compartido.Tasa();
            result.setId(target.getId());
            result.setCodigo(target.getCodigo());
            result.setImporte(target.getImporte());
            if (target.hasDescripcion()) {
                result.setDescripcion(target.getDescripcion());
            }
            return result;
        }
    }

    public static class Restricciones {

        public cat.ogasoft.examples2.Compartido.Restricciones dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Restricciones result = cat.ogasoft.protocolizer.messages.Compartido.Restricciones.parseFrom(target);
            return buildRestricciones(result);
        }

        public static cat.ogasoft.examples2.Compartido.Restricciones buildRestricciones(cat.ogasoft.protocolizer.messages.Compartido.Restricciones target) {
            cat.ogasoft.examples2.Compartido.Restricciones result = new cat.ogasoft.examples2.Compartido.Restricciones();
            if (target.hasMinimoVenta()) {
                result.setMinimoVenta(target.getMinimoVenta());
            }
            if (target.hasModuloVenta()) {
                result.setModuloVenta(target.getModuloVenta());
            }
            return result;
        }
    }

    public static class Kit {

        public cat.ogasoft.examples2.Compartido.Kit dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Kit result = cat.ogasoft.protocolizer.messages.Compartido.Kit.parseFrom(target);
            return buildKit(result);
        }

        public static cat.ogasoft.examples2.Compartido.Kit buildKit(cat.ogasoft.protocolizer.messages.Compartido.Kit target) {
            cat.ogasoft.examples2.Compartido.Kit result = new cat.ogasoft.examples2.Compartido.Kit();
            if (target.hasArticuloKit()) {
                result.setArticuloKit(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Articulo.buildArticulo(target.getArticuloKit()));
            }
            if (target.getComponenteKitList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Articulo> r = new java.util.ArrayList<>(target.getComponenteKitCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Articulo k : target.getComponenteKitList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Articulo.buildArticulo(k));
                }
                result.setComponenteKit(r);
            }
            {
                java.util.ArrayList<Double> r = new java.util.ArrayList<>(target.getCantidadComponenteCount());
                for (Double k : target.getCantidadComponenteList()) {
                    r.add(k);
                }
                result.setCantidadComponente(r);
            }
            return result;
        }
    }

    public static class Articulo {

        public cat.ogasoft.examples2.Compartido.Articulo dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Articulo result = cat.ogasoft.protocolizer.messages.Compartido.Articulo.parseFrom(target);
            return buildArticulo(result);
        }

        public static cat.ogasoft.examples2.Compartido.Articulo buildArticulo(cat.ogasoft.protocolizer.messages.Compartido.Articulo target) {
            cat.ogasoft.examples2.Compartido.Articulo result = new cat.ogasoft.examples2.Compartido.Articulo();
            result.setArticuloId(target.getArticuloId());
            if (target.hasIdentificacion()) {
                result.setIdentificacion(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Identificacion.buildIdentificacion(target.getIdentificacion()));
            }
            if (target.hasStock()) {
                result.setStock(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Stock.buildStock(target.getStock()));
            }
            if (target.getStocksList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Stock> r = new java.util.ArrayList<>(target.getStocksCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Stock k : target.getStocksList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Stock.buildStock(k));
                }
                result.setStocks(r);
            }
            if (target.getPreciosList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Precio> r = new java.util.ArrayList<>(target.getPreciosCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Precio k : target.getPreciosList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Precio.buildPrecio(k));
                }
                result.setPrecios(r);
            }
            if (target.hasReferenciaPrincipal()) {
                result.setReferenciaPrincipal(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Referencia.buildReferencia(target.getReferenciaPrincipal()));
            }
            if (target.getReferenciasList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Referencia> r = new java.util.ArrayList<>(target.getReferenciasCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Referencia k : target.getReferenciasList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Referencia.buildReferencia(k));
                }
                result.setReferencias(r);
            }
            if (target.hasDescripcion()) {
                result.setDescripcion(target.getDescripcion());
            }
            if (target.hasMarca()) {
                result.setMarca(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Marca.buildMarca(target.getMarca()));
            }
            if (target.getEquivalentesList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Articulo> r = new java.util.ArrayList<>(target.getEquivalentesCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Articulo k : target.getEquivalentesList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Articulo.buildArticulo(k));
                }
                result.setEquivalentes(r);
            }
            if (target.hasCascoEnvase()) {
                result.setCascoEnvase(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Articulo.buildArticulo(target.getCascoEnvase()));
            }
            if (target.hasRestricciones()) {
                result.setRestricciones(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Restricciones.buildRestricciones(target.getRestricciones()));
            }
            if (target.getTasasList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Tasa> r = new java.util.ArrayList<>(target.getTasasCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Tasa k : target.getTasasList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Tasa.buildTasa(k));
                }
                result.setTasas(r);
            }
            if (target.getKitsList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Kit> r = new java.util.ArrayList<>(target.getKitsCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Kit k : target.getKitsList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Kit.buildKit(k));
                }
                result.setKits(r);
            }
            if (target.getOfertasList() != null) {
                java.util.ArrayList<cat.ogasoft.examples2.Compartido.Oferta> r = new java.util.ArrayList<>(target.getOfertasCount());
                for (cat.ogasoft.protocolizer.messages.Compartido.Oferta k : target.getOfertasList()) {
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Oferta.buildOferta(k));
                }
                result.setOfertas(r);
            }
            if (target.hasGenerico()) {
                result.setGenerico(target.getGenerico());
            }
            return result;
        }
    }

    public static class Direccion {

        public cat.ogasoft.examples2.Compartido.Direccion dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Direccion result = cat.ogasoft.protocolizer.messages.Compartido.Direccion.parseFrom(target);
            return buildDireccion(result);
        }

        public static cat.ogasoft.examples2.Compartido.Direccion buildDireccion(cat.ogasoft.protocolizer.messages.Compartido.Direccion target) {
            cat.ogasoft.examples2.Compartido.Direccion result = new cat.ogasoft.examples2.Compartido.Direccion();
            if (target.hasDireccion1()) {
                result.setDireccion1(target.getDireccion1());
            }
            if (target.hasDireccion2()) {
                result.setDireccion2(target.getDireccion2());
            }
            if (target.hasNumero()) {
                result.setNumero(target.getNumero());
            }
            if (target.hasEscalera()) {
                result.setEscalera(target.getEscalera());
            }
            if (target.hasPiso()) {
                result.setPiso(target.getPiso());
            }
            if (target.hasPuerta()) {
                result.setPuerta(target.getPuerta());
            }
            if (target.hasCodigoPostal()) {
                result.setCodigoPostal(target.getCodigoPostal());
            }
            if (target.hasPoblacion()) {
                result.setPoblacion(target.getPoblacion());
            }
            if (target.hasProvincia()) {
                result.setProvincia(target.getProvincia());
            }
            if (target.hasPais()) {
                result.setPais(target.getPais());
            }
            if (target.hasNif()) {
                result.setNif(target.getNif());
            }
            return result;
        }
    }

    public static class Archivo {

        public cat.ogasoft.examples2.Compartido.Archivo dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.Archivo result = cat.ogasoft.protocolizer.messages.Compartido.Archivo.parseFrom(target);
            return buildArchivo(result);
        }

        public static cat.ogasoft.examples2.Compartido.Archivo buildArchivo(cat.ogasoft.protocolizer.messages.Compartido.Archivo target) {
            cat.ogasoft.examples2.Compartido.Archivo result = new cat.ogasoft.examples2.Compartido.Archivo();
            result.setIdArchivo(target.getIdArchivo());
            result.setTipo(cat.ogasoft.examples2.Compartido.TipoArchivo.valueOf(target.getTipo().name()));
            return result;
        }
    }

    public static class DatosTaller {

        public cat.ogasoft.examples2.Compartido.DatosTaller dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.Compartido.DatosTaller result = cat.ogasoft.protocolizer.messages.Compartido.DatosTaller.parseFrom(target);
            return buildDatosTaller(result);
        }

        public static cat.ogasoft.examples2.Compartido.DatosTaller buildDatosTaller(cat.ogasoft.protocolizer.messages.Compartido.DatosTaller target) {
            cat.ogasoft.examples2.Compartido.DatosTaller result = new cat.ogasoft.examples2.Compartido.DatosTaller();
            result.setNombreComercial(target.getNombreComercial());
            result.setDireccion(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Direccion.buildDireccion(target.getDireccion()));
            if (target.hasTelefono()) {
                result.setTelefono(target.getTelefono());
            }
            if (target.hasMovil()) {
                result.setMovil(target.getMovil());
            }
            if (target.hasEmail()) {
                result.setEmail(target.getEmail());
            }
            if (target.hasPrecioHora()) {
                result.setPrecioHora(target.getPrecioHora());
            }
            if (target.hasPorcentajeImpuesto()) {
                result.setPorcentajeImpuesto(target.getPorcentajeImpuesto());
            }
            if (target.hasTituloImpuesto()) {
                result.setTituloImpuesto(target.getTituloImpuesto());
            }
            return result;
        }
    }

}
