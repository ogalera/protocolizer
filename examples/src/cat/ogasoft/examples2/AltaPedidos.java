/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples2;

import cat.ogasoft.examples2.Compartido.Identificacion;
import cat.ogasoft.examples2.Compartido.Precio;
import cat.ogasoft.examples2.Compartido.Producto;
import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 29, 2017 [11:59:20 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "AltaPedidos", generateSource = true)
@ProtoFileV2.Dumpper(type = ProtoFileV2.Dumpper.DumpperTypes.BOTH)
@ProtoFileV2.File.Import(importClass = Compartido.class)
public class AltaPedidos {

    @ProtoFileV2.File.Message
    public static class AltaMaestroPedido {

        @ProtoFileV2.File.Message.Field
        private String sucursalId;
        @ProtoFileV2.File.Message.Field
        private boolean anulaPendientes;
        @ProtoFileV2.File.Message.Field
        private boolean generaPendientes;
        @ProtoFileV2.File.Message.Field
        private boolean enviarReparto;
        @ProtoFileV2.File.Message.Field
        private boolean urgente;
        @ProtoFileV2.File.Message.Field
        private String fechaEntrega;
        @ProtoFileV2.File.Message.Field
        private String fechaPedido;
        @ProtoFileV2.File.Message.Field
        private Producto productoQueEnvia;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String personaContacto;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String observaciones;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String numeroPedido;

        public String getSucursalId() {
            return sucursalId;
        }

        public void setSucursalId(String sucursalId) {
            this.sucursalId = sucursalId;
        }

        public boolean isAnulaPendientes() {
            return anulaPendientes;
        }

        public void setAnulaPendientes(boolean anulaPendientes) {
            this.anulaPendientes = anulaPendientes;
        }

        public boolean isGeneraPendientes() {
            return generaPendientes;
        }

        public void setGeneraPendientes(boolean generaPendientes) {
            this.generaPendientes = generaPendientes;
        }

        public boolean isEnviarReparto() {
            return enviarReparto;
        }

        public void setEnviarReparto(boolean enviarReparto) {
            this.enviarReparto = enviarReparto;
        }

        public boolean isUrgente() {
            return urgente;
        }

        public void setUrgente(boolean urgente) {
            this.urgente = urgente;
        }

        public String getFechaEntrega() {
            return fechaEntrega;
        }

        public void setFechaEntrega(String fechaEntrega) {
            this.fechaEntrega = fechaEntrega;
        }

        public String getFechaPedido() {
            return fechaPedido;
        }

        public void setFechaPedido(String fechaPedido) {
            this.fechaPedido = fechaPedido;
        }

        public Producto getProductoQueEnvia() {
            return productoQueEnvia;
        }

        public void setProductoQueEnvia(Producto productoQueEnvia) {
            this.productoQueEnvia = productoQueEnvia;
        }

        public String getPersonaContacto() {
            return personaContacto;
        }

        public void setPersonaContacto(String personaContacto) {
            this.personaContacto = personaContacto;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

        public String getNumeroPedido() {
            return numeroPedido;
        }

        public void setNumeroPedido(String numeroPedido) {
            this.numeroPedido = numeroPedido;
        }

    }

    @ProtoFileV2.File.Message
    public static class AltaDetallePedido {

        @ProtoFileV2.File.Message.Field
        private Identificacion articulo;
        @ProtoFileV2.File.Message.Field
        private double unidades;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Precio precio;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String observaciones;

        public Identificacion getArticulo() {
            return articulo;
        }

        public void setArticulo(Identificacion articulo) {
            this.articulo = articulo;
        }

        public double getUnidades() {
            return unidades;
        }

        public void setUnidades(double unidades) {
            this.unidades = unidades;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Precio getPrecio() {
            return precio;
        }

        public void setPrecio(Precio precio) {
            this.precio = precio;
        }

        public String getObservaciones() {
            return observaciones;
        }

        public void setObservaciones(String observaciones) {
            this.observaciones = observaciones;
        }

    }

    @ProtoFileV2.File.Message
    public static class AltaPedidoReq {

        @ProtoFileV2.File.Message.Field
        private int uid;
        @ProtoFileV2.File.Message.Field
        private AltaMaestroPedido maestroPedido;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<AltaDetallePedido> detallesPedido;

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public AltaMaestroPedido getMaestroPedido() {
            return maestroPedido;
        }

        public void setMaestroPedido(AltaMaestroPedido maestroPedido) {
            this.maestroPedido = maestroPedido;
        }

        public List<AltaDetallePedido> getDetallesPedido() {
            return detallesPedido;
        }

        public void setDetallesPedido(List<AltaDetallePedido> detallesPedido) {
            this.detallesPedido = detallesPedido;
        }

    }

    @ProtoFileV2.File.Message
    public static class AltaPedidosRes {

        @ProtoFileV2.File.Message.Field
        private String idPedido;
        @ProtoFileV2.File.Message.Field
        private String numeroPedido;
        @ProtoFileV2.File.Message.Field
        private boolean hayError;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String mensajeError;

        public String getIdPedido() {
            return idPedido;
        }

        public void setIdPedido(String idPedido) {
            this.idPedido = idPedido;
        }

        public String getNumeroPedido() {
            return numeroPedido;
        }

        public void setNumeroPedido(String numeroPedido) {
            this.numeroPedido = numeroPedido;
        }

        public boolean isHayError() {
            return hayError;
        }

        public void setHayError(boolean hayError) {
            this.hayError = hayError;
        }

        public String getMensajeError() {
            return mensajeError;
        }

        public void setMensajeError(String mensajeError) {
            this.mensajeError = mensajeError;
        }

    }
}
