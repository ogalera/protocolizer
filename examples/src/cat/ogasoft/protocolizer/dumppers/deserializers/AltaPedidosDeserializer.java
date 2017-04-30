//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class AltaPedidosDeserializer {
    public static class AltaMaestroPedido {
        public cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido result = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido.parseFrom(target);
            return buildAltaMaestroPedido(result);
        }
        public static cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido buildAltaMaestroPedido(cat.ogasoft.protocolizer.messages.AltaPedidos.AltaMaestroPedido target){
            cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido result = new cat.ogasoft.examples2.AltaPedidos.AltaMaestroPedido();
            result.setSucursalId(target.getSucursalId());
            result.setAnulaPendientes(target.getAnulaPendientes());
            result.setGeneraPendientes(target.getGeneraPendientes());
            result.setEnviarReparto(target.getEnviarReparto());
            result.setUrgente(target.getUrgente());
            result.setFechaEntrega(target.getFechaEntrega());
            result.setFechaPedido(target.getFechaPedido());
            result.setProductoQueEnvia(cat.ogasoft.examples2.Compartido.Producto.valueOf(target.getProductoQueEnvia().name()));
            if(target.hasPersonaContacto()){
                result.setPersonaContacto(target.getPersonaContacto());
            }
            if(target.hasObservaciones()){
                result.setObservaciones(target.getObservaciones());
            }
            if(target.hasNumeroPedido()){
                result.setNumeroPedido(target.getNumeroPedido());
            }
            return result;
        }
    }

    public static class AltaDetallePedido {
        public cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido result = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido.parseFrom(target);
            return buildAltaDetallePedido(result);
        }
        public static cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido buildAltaDetallePedido(cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido target){
            cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido result = new cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido();
            result.setArticulo(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Identificacion.buildIdentificacion(target.getArticulo()));
            result.setUnidades(target.getUnidades());
            if(target.hasDescripcion()){
                result.setDescripcion(target.getDescripcion());
            }
            if(target.hasPrecio()){
                result.setPrecio(cat.ogasoft.protocolizer.dumppers.deserializers.CompartidoDeserializer.Precio.buildPrecio(target.getPrecio()));
            }
            if(target.hasObservaciones()){
                result.setObservaciones(target.getObservaciones());
            }
            return result;
        }
    }

    public static class AltaPedidoReq {
        public cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq result = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq.parseFrom(target);
            return buildAltaPedidoReq(result);
        }
        public static cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq buildAltaPedidoReq(cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidoReq target){
            cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq result = new cat.ogasoft.examples2.AltaPedidos.AltaPedidoReq();
            result.setUid(target.getUid());
            result.setMaestroPedido(cat.ogasoft.protocolizer.dumppers.deserializers.AltaPedidosDeserializer.AltaMaestroPedido.buildAltaMaestroPedido(target.getMaestroPedido()));
            if(target.getDetallesPedidoList() != null){
                java.util.ArrayList<cat.ogasoft.examples2.AltaPedidos.AltaDetallePedido> r = new java.util.ArrayList<>(target.getDetallesPedidoCount());
                for(cat.ogasoft.protocolizer.messages.AltaPedidos.AltaDetallePedido k:target.getDetallesPedidoList()){
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.AltaPedidosDeserializer.AltaDetallePedido.buildAltaDetallePedido(k));
                }
                result.setDetallesPedido(r);
            }
            return result;
        }
    }

    public static class AltaPedidosRes {
        public cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes result = cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes.parseFrom(target);
            return buildAltaPedidosRes(result);
        }
        public static cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes buildAltaPedidosRes(cat.ogasoft.protocolizer.messages.AltaPedidos.AltaPedidosRes target){
            cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes result = new cat.ogasoft.examples2.AltaPedidos.AltaPedidosRes();
            result.setIdPedido(target.getIdPedido());
            result.setNumeroPedido(target.getNumeroPedido());
            result.setHayError(target.getHayError());
            if(target.hasMensajeError()){
                result.setMensajeError(target.getMensajeError());
            }
            return result;
        }
    }

}
