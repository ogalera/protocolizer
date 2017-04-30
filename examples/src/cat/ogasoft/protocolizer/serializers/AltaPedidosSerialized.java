//Protocolizer 30/04/2017 11:13:09
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.serializers;


public class AltaPedidosSerialized {
    public static class AltaMaestroPedido {
        public byte[] dump(cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido target){
            return buildAltaMaestroPedido(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido buildAltaMaestroPedido(cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido target){
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido.Builder builder = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido.newBuilder();
            builder.setSucursalId(target.getSucursalId());
            builder.setAnulaPendientes(target.isAnulaPendientes());
            builder.setGeneraPendientes(target.isGeneraPendientes());
            builder.setEnviarReparto(target.isEnviarReparto());
            builder.setUrgente(target.isUrgente());
            builder.setFechaEntrega(target.getFechaEntrega());
            builder.setFechaPedido(target.getFechaPedido());
            builder.setProductoQueEnvia(cat.ogasoft.protocolizer.messages.Compartido.Producto.valueOf(target.getProductoQueEnvia().name()));
            if(target.getPersonaContacto() != null){
                builder.setPersonaContacto(target.getPersonaContacto());
            }
            if(target.getObservaciones() != null){
                builder.setObservaciones(target.getObservaciones());
            }
            if(target.getNumeroPedido() != null){
                builder.setNumeroPedido(target.getNumeroPedido());
            }
            return builder.build();
        }
    }

    public static class AltaDetallePedido {
        public byte[] dump(cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido target){
            return buildAltaDetallePedido(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido buildAltaDetallePedido(cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido target){
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido.Builder builder = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido.newBuilder();
            builder.setArticulo(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Identificacion.buildIdentificacion(target.getArticulo()));
            builder.setUnidades(target.getUnidades());
            if(target.getDescripcion() != null){
                builder.setDescripcion(target.getDescripcion());
            }
            if(target.getPrecio() != null){
                builder.setPrecio(cat.ogasoft.protocolizer.serializers.CompartidoSerialized.Precio.buildPrecio(target.getPrecio()));
            }
            if(target.getObservaciones() != null){
                builder.setObservaciones(target.getObservaciones());
            }
            return builder.build();
        }
    }

    public static class AltaPedidoReq {
        public byte[] dump(cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq target){
            return buildAltaPedidoReq(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq buildAltaPedidoReq(cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq target){
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq.Builder builder = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq.newBuilder();
            builder.setUid(target.getUid());
            builder.setMaestroPedido(cat.ogasoft.protocolizer.serializers.AltaPedidosSerialized.AltaMaestroPedido.buildAltaMaestroPedido(target.getMaestroPedido()));
            if(target.getDetallesPedido() != null){
                for(cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido k:target.getDetallesPedido()){
                    builder.addDetallesPedido(cat.ogasoft.protocolizer.serializers.AltaPedidosSerialized.AltaDetallePedido.buildAltaDetallePedido(k));
                }
            }
            return builder.build();
        }
    }

    public static class AltaPedidosRes {
        public byte[] dump(cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes target){
            return buildAltaPedidosRes(target).toByteArray();
        }
        public static cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes buildAltaPedidosRes(cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes target){
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes.Builder builder = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes.newBuilder();
            builder.setIdPedido(target.getIdPedido());
            builder.setNumeroPedido(target.getNumeroPedido());
            builder.setHayError(target.isHayError());
            if(target.getMensajeError() != null){
                builder.setMensajeError(target.getMensajeError());
            }
            return builder.build();
        }
    }

}
