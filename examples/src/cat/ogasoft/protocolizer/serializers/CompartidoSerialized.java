//Protocolizer 30/04/2017 11:13:09
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com
package cat.ogasoft.protocolizer.serializers;

public class CompartidoSerialized {

    public static class Referencia extends Thread implements cat.ogasoft.protocolizer.SerializerProtoWorker<cat.ogasoft.examples2.Compartido.Referencia> {

        private byte[] bytes;
        private cat.ogasoft.examples2.Compartido.Referencia container;

        @Override
        public void work(cat.ogasoft.examples2.Compartido.Referencia container) {
            this.container = container;
            super.setName("cat.ogasoft.protocolizer.serializers.CompartidoSerialized.ReferenciaWorker");
            start();
        }

        @Override
        public byte[] waitUntilEnd() throws InterruptedException {
            this.join();
            return bytes;
        }

        @Override
        public void run() {
            bytes = dump(container);
        }

        public byte[] dump(cat.ogasoft.examples2.Compartido.Referencia target) {
            return buildReferencia(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Referencia buildReferencia(cat.ogasoft.examples2.Compartido.Referencia target) {
            cat.ogasoft.protocolizer.messages.Compartido.Referencia.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Referencia.newBuilder();
            builder.setReferencia(target.getReferencia());
            builder.setTipo(cat.ogasoft.protocolizer.messages.Compartido.TipoReferencia.valueOf(target.getTipo().name()));
            return builder.build();
        }
    }

    public static class Marca {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Marca target) {
            return buildMarca(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Marca buildMarca(cat.ogasoft.examples2.Compartido.Marca target) {
            cat.ogasoft.protocolizer.messages.Compartido.Marca.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Marca.newBuilder();
            builder.setMarca(target.getMarca());
            builder.setTipo(cat.ogasoft.protocolizer.messages.Compartido.TipoMarca.valueOf(target.getTipo().name()));
            if (target.getNombre() != null) {
                builder.setNombre(target.getNombre());
            }
            return builder.build();
        }
    }

    public static class Identificacion {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Identificacion target) {
            return buildIdentificacion(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Identificacion buildIdentificacion(cat.ogasoft.examples2.Compartido.Identificacion target) {
            cat.ogasoft.protocolizer.messages.Compartido.Identificacion.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Identificacion.newBuilder();
            if (target.getReferencia() != null) {
                builder.setReferencia(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Referencia.buildReferencia(target.getReferencia()));
            }
            if (target.getMarca() != null) {
                builder.setMarca(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Marca.buildMarca(target.getMarca()));
            }
            if (target.getOtras() != null) {
                for (cat.ogasoft.examples2.Compartido.Referencia k : target.getOtras()) {
                    builder.addOtras(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Referencia.buildReferencia(k));
                }
            }
            if (target.getArticuloId() != null) {
                builder.setArticuloId(target.getArticuloId());
            }
            if (target.getInformacionAdicional() != null) {
                for (cat.ogasoft.examples2.Compartido.Amplia k : target.getInformacionAdicional()) {
                    builder.addInformacionAdicional(cat.ogasoft.protocolizer.messages.Compartido.Amplia.valueOf(k.name()));
                }
            }
            return builder.build();
        }
    }

    public static class Precio {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Precio target) {
            return buildPrecio(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Precio buildPrecio(cat.ogasoft.examples2.Compartido.Precio target) {
            cat.ogasoft.protocolizer.messages.Compartido.Precio.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Precio.newBuilder();
            if (target.getPrecio() != 0.0) {
                builder.setPrecio(target.getPrecio());
            }
            if (target.getDescuento() != 0.0) {
                builder.setDescuento(target.getDescuento());
            }
            builder.setTipo(cat.ogasoft.protocolizer.messages.Compartido.TipoPrecio.valueOf(target.getTipo().name()));
            return builder.build();
        }
    }

    public static class Promocion {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Promocion target) {
            return buildPromocion(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Promocion buildPromocion(cat.ogasoft.examples2.Compartido.Promocion target) {
            cat.ogasoft.protocolizer.messages.Compartido.Promocion.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Promocion.newBuilder();
            builder.setEnVenta(target.isEnVenta());
            if (target.getTitulo() != null) {
                builder.setTitulo(target.getTitulo());
            }
            if (target.getDescripcion() != null) {
                builder.setDescripcion(target.getDescripcion());
            }
            if (target.getImagenId() != 0) {
                builder.setImagenId(target.getImagenId());
            }
            return builder.build();
        }
    }

    public static class Oferta {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Oferta target) {
            return buildOferta(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Oferta buildOferta(cat.ogasoft.examples2.Compartido.Oferta target) {
            cat.ogasoft.protocolizer.messages.Compartido.Oferta.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Oferta.newBuilder();
            builder.setAplicacion(cat.ogasoft.protocolizer.messages.Compartido.AplicacionOferta.valueOf(target.getAplicacion().name()));
            builder.setVisible(cat.ogasoft.protocolizer.messages.Compartido.VisualizacionOferta.valueOf(target.getVisible().name()));
            if (target.getValorAplicacion() != 0.0) {
                builder.setValorAplicacion(target.getValorAplicacion());
            }
            if (target.getDescripcion() != null) {
                builder.setDescripcion(target.getDescripcion());
            }
            if (target.getModificarPVP() != 0.0) {
                builder.setModificarPVP(target.getModificarPVP());
            }
            if (target.getModificarDTO() != 0.0) {
                builder.setModificarDTO(target.getModificarDTO());
            }
            if (target.getArticuloRegalo() != null) {
                builder.setArticuloRegalo(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Articulo.buildArticulo(target.getArticuloRegalo()));
            }
            if (target.getCantidadRegalo() != 0.0) {
                builder.setCantidadRegalo(target.getCantidadRegalo());
            }
            if (target.getIdAnuncioEnlazar() != null) {
                builder.setIdAnuncioEnlazar(target.getIdAnuncioEnlazar());
            }
            return builder.build();
        }
    }

    public static class Stock {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Stock target) {
            return buildStock(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Stock buildStock(cat.ogasoft.examples2.Compartido.Stock target) {
            cat.ogasoft.protocolizer.messages.Compartido.Stock.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Stock.newBuilder();
            if (target.getSucursalId() != null) {
                builder.setSucursalId(target.getSucursalId());
            }
            if (target.getUbicacion() != null) {
                builder.setUbicacion(target.getUbicacion());
            }
            builder.setStock(target.getStock());
            builder.setStockMostrar(target.getStockMostrar());
            return builder.build();
        }
    }

    public static class Tasa {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Tasa target) {
            return buildTasa(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Tasa buildTasa(cat.ogasoft.examples2.Compartido.Tasa target) {
            cat.ogasoft.protocolizer.messages.Compartido.Tasa.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Tasa.newBuilder();
            builder.setId(target.getId());
            builder.setCodigo(target.getCodigo());
            builder.setImporte(target.getImporte());
            if (target.getDescripcion() != null) {
                builder.setDescripcion(target.getDescripcion());
            }
            return builder.build();
        }
    }

    public static class Restricciones {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Restricciones target) {
            return buildRestricciones(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Restricciones buildRestricciones(cat.ogasoft.examples2.Compartido.Restricciones target) {
            cat.ogasoft.protocolizer.messages.Compartido.Restricciones.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Restricciones.newBuilder();
            if (target.getMinimoVenta() != 0.0) {
                builder.setMinimoVenta(target.getMinimoVenta());
            }
            if (target.getModuloVenta() != 0.0) {
                builder.setModuloVenta(target.getModuloVenta());
            }
            return builder.build();
        }
    }

    public static class Kit {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Kit target) {
            return buildKit(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Kit buildKit(cat.ogasoft.examples2.Compartido.Kit target) {
            cat.ogasoft.protocolizer.messages.Compartido.Kit.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Kit.newBuilder();
            if (target.getArticuloKit() != null) {
                builder.setArticuloKit(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Articulo.buildArticulo(target.getArticuloKit()));
            }
            if (target.getComponenteKit() != null) {
                for (cat.ogasoft.examples2.Compartido.Articulo k : target.getComponenteKit()) {
                    builder.addComponenteKit(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Articulo.buildArticulo(k));
                }
            }
            for (double k : target.getCantidadComponente()) {
                builder.addCantidadComponente(k);
            }
            return builder.build();
        }
    }

    public static class Articulo {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Articulo target) {
            return buildArticulo(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Articulo buildArticulo(cat.ogasoft.examples2.Compartido.Articulo target) {
            cat.ogasoft.protocolizer.messages.Compartido.Articulo.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Articulo.newBuilder();
            builder.setArticuloId(target.getArticuloId());
            if (target.getIdentificacion() != null) {
                builder.setIdentificacion(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Identificacion.buildIdentificacion(target.getIdentificacion()));
            }
            if (target.getStock() != null) {
                builder.setStock(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Stock.buildStock(target.getStock()));
            }
            if (target.getStocks() != null) {
                for (cat.ogasoft.examples2.Compartido.Stock k : target.getStocks()) {
                    builder.addStocks(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Stock.buildStock(k));
                }
            }
            if (target.getPrecios() != null) {
                for (cat.ogasoft.examples2.Compartido.Precio k : target.getPrecios()) {
                    builder.addPrecios(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Precio.buildPrecio(k));
                }
            }
            if (target.getReferenciaPrincipal() != null) {
                builder.setReferenciaPrincipal(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Referencia.buildReferencia(target.getReferenciaPrincipal()));
            }
            if (target.getReferencias() != null) {
                for (cat.ogasoft.examples2.Compartido.Referencia k : target.getReferencias()) {
                    builder.addReferencias(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Referencia.buildReferencia(k));
                }
            }
            if (target.getDescripcion() != null) {
                builder.setDescripcion(target.getDescripcion());
            }
            if (target.getMarca() != null) {
                builder.setMarca(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Marca.buildMarca(target.getMarca()));
            }
            if (target.getEquivalentes() != null) {
                for (cat.ogasoft.examples2.Compartido.Articulo k : target.getEquivalentes()) {
                    builder.addEquivalentes(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Articulo.buildArticulo(k));
                }
            }
            if (target.getCascoEnvase() != null) {
                builder.setCascoEnvase(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Articulo.buildArticulo(target.getCascoEnvase()));
            }
            if (target.getRestricciones() != null) {
                builder.setRestricciones(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Restricciones.buildRestricciones(target.getRestricciones()));
            }
            if (target.getTasas() != null) {
                for (cat.ogasoft.examples2.Compartido.Tasa k : target.getTasas()) {
                    builder.addTasas(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Tasa.buildTasa(k));
                }
            }
            if (target.getKits() != null) {
                for (cat.ogasoft.examples2.Compartido.Kit k : target.getKits()) {
                    builder.addKits(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Kit.buildKit(k));
                }
            }
            if (target.getOfertas() != null) {
                for (cat.ogasoft.examples2.Compartido.Oferta k : target.getOfertas()) {
                    builder.addOfertas(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Oferta.buildOferta(k));
                }
            }
            if (target.isGenerico()) {
                builder.setGenerico(target.isGenerico());
            }
            return builder.build();
        }
    }

    public static class Direccion {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Direccion target) {
            return buildDireccion(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Direccion buildDireccion(cat.ogasoft.examples2.Compartido.Direccion target) {
            cat.ogasoft.protocolizer.messages.Compartido.Direccion.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Direccion.newBuilder();
            if (target.getDireccion1() != null) {
                builder.setDireccion1(target.getDireccion1());
            }
            if (target.getDireccion2() != null) {
                builder.setDireccion2(target.getDireccion2());
            }
            if (target.getNumero() != null) {
                builder.setNumero(target.getNumero());
            }
            if (target.getEscalera() != null) {
                builder.setEscalera(target.getEscalera());
            }
            if (target.getPiso() != null) {
                builder.setPiso(target.getPiso());
            }
            if (target.getPuerta() != null) {
                builder.setPuerta(target.getPuerta());
            }
            if (target.getCodigoPostal() != null) {
                builder.setCodigoPostal(target.getCodigoPostal());
            }
            if (target.getPoblacion() != null) {
                builder.setPoblacion(target.getPoblacion());
            }
            if (target.getProvincia() != null) {
                builder.setProvincia(target.getProvincia());
            }
            if (target.getPais() != null) {
                builder.setPais(target.getPais());
            }
            if (target.getNif() != null) {
                builder.setNif(target.getNif());
            }
            return builder.build();
        }
    }

    public static class Archivo {

        public byte[] dump(cat.ogasoft.examples2.Compartido.Archivo target) {
            return buildArchivo(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.Archivo buildArchivo(cat.ogasoft.examples2.Compartido.Archivo target) {
            cat.ogasoft.protocolizer.messages.Compartido.Archivo.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.Archivo.newBuilder();
            builder.setIdArchivo(target.getIdArchivo());
            builder.setTipo(cat.ogasoft.protocolizer.messages.Compartido.TipoArchivo.valueOf(target.getTipo().name()));
            return builder.build();
        }
    }

    public static class DatosTaller {

        public byte[] dump(cat.ogasoft.examples2.Compartido.DatosTaller target) {
            return buildDatosTaller(target).toByteArray();
        }

        public static cat.ogasoft.protocolizer.messages.Compartido.DatosTaller buildDatosTaller(cat.ogasoft.examples2.Compartido.DatosTaller target) {
            cat.ogasoft.protocolizer.messages.Compartido.DatosTaller.Builder builder = cat.ogasoft.protocolizer.messages.Compartido.DatosTaller.newBuilder();
            builder.setNombreComercial(target.getNombreComercial());
            builder.setDireccion(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Direccion.buildDireccion(target.getDireccion()));
            if (target.getTelefono() != null) {
                builder.setTelefono(target.getTelefono());
            }
            if (target.getMovil() != null) {
                builder.setMovil(target.getMovil());
            }
            if (target.getEmail() != null) {
                builder.setEmail(target.getEmail());
            }
            if (target.getPrecioHora() != 0.0) {
                builder.setPrecioHora(target.getPrecioHora());
            }
            if (target.getPorcentajeImpuesto() != 0.0) {
                builder.setPorcentajeImpuesto(target.getPorcentajeImpuesto());
            }
            if (target.getTituloImpuesto() != null) {
                builder.setTituloImpuesto(target.getTituloImpuesto());
            }
            return builder.build();
        }
    }

}
