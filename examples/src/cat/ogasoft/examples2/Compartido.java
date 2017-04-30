/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples2;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 29, 2017 [11:10:46 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "Compartido", generateSource = true)
@ProtoFileV2.Serialize
public class Compartido {

    @ProtoFileV2.File.Enum
    public static enum TipoMarca {
        MARCA_AD_GLOBAL,
        MARCA_PROPIA;
    }

    @ProtoFileV2.File.Enum
    public static enum TipoReferencia {
        REF_PROPIA,
        PROVEEDOR,
        EAN,
        CAI,
        OEM,
        DESCONOCIDO
    }

    @ProtoFileV2.File.Enum
    public static enum TipoPrecio {
        VENTA,
        CATALOGO,
        TARIFA_PROVEEDOR
    }

    @ProtoFileV2.File.Enum
    public static enum Idioma {
        NOIDIOMA,
        CATALA,
        CASTELLANO,
        EUSKARA,
        GALEGO,
        PORTUGUES
    }

    @ProtoFileV2.File.Enum
    public static enum Amplia {
        STOCK_SIMPLE,
        STOCK_DESGLOSADO,
        PRECIOS,
        PRECIO_VENTA,
        PRECIO_CATALOGO,
        PRECIO_TARIFA,
        REFERENCIA_PRINCIPAL,
        REFERENCIAS_TODAS,
        EQUIVALENTES,
        CASCOS_ENVASES,
        RESTRICCIONES_VENTA,
        TASAS,
        TODO,
        KITS,
        OFERTAS
    }

    @ProtoFileV2.File.Enum
    public static enum Producto {
        AD360,
        ADCONNECT,
        ADTABLET
    }

    @ProtoFileV2.File.Message
    public static class Referencia {

        @ProtoFileV2.File.Message.Field
        private String referencia;

        @ProtoFileV2.File.Message.Field
        private TipoReferencia tipo;

        public String getReferencia() {
            return referencia;
        }

        public void setReferencia(String referencia) {
            this.referencia = referencia;
        }

        public TipoReferencia getTipo() {
            return tipo;
        }

        public void setTipo(TipoReferencia tipo) {
            this.tipo = tipo;
        }

    }

    @ProtoFileV2.File.Message
    public static class Marca {

        @ProtoFileV2.File.Message.Field
        private String marca;
        @ProtoFileV2.File.Message.Field
        private TipoMarca tipo;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String nombre;

        public String getMarca() {
            return marca;
        }

        public void setMarca(String marca) {
            this.marca = marca;
        }

        public TipoMarca getTipo() {
            return tipo;
        }

        public void setTipo(TipoMarca tipo) {
            this.tipo = tipo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
    }

    @ProtoFileV2.File.Message
    public static class Identificacion {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Referencia referencia;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Marca marca;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Referencia> otras;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String articuloId;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Amplia> informacionAdicional;

        public Referencia getReferencia() {
            return referencia;
        }

        public void setReferencia(Referencia referencia) {
            this.referencia = referencia;
        }

        public Marca getMarca() {
            return marca;
        }

        public void setMarca(Marca marca) {
            this.marca = marca;
        }

        public List<Referencia> getOtras() {
            return otras;
        }

        public void setOtras(List<Referencia> otras) {
            this.otras = otras;
        }

        public String getArticuloId() {
            return articuloId;
        }

        public void setArticuloId(String articuloId) {
            this.articuloId = articuloId;
        }

        public List<Amplia> getInformacionAdicional() {
            return informacionAdicional;
        }

        public void setInformacionAdicional(List<Amplia> informacionAdicional) {
            this.informacionAdicional = informacionAdicional;
        }

    }

    @ProtoFileV2.File.Message
    public static class Precio {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double precio;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double descuento;
        @ProtoFileV2.File.Message.Field
        private TipoPrecio tipo;

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        public double getDescuento() {
            return descuento;
        }

        public void setDescuento(double descuento) {
            this.descuento = descuento;
        }

        public TipoPrecio getTipo() {
            return tipo;
        }

        public void setTipo(TipoPrecio tipo) {
            this.tipo = tipo;
        }

    }

    @ProtoFileV2.File.Message
    public static class Promocion {

        @ProtoFileV2.File.Message.Field
        private boolean enVenta;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String titulo;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private int imagenId;

        public boolean isEnVenta() {
            return enVenta;
        }

        public void setEnVenta(boolean enVenta) {
            this.enVenta = enVenta;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public int getImagenId() {
            return imagenId;
        }

        public void setImagenId(int imagenId) {
            this.imagenId = imagenId;
        }

    }

    @ProtoFileV2.File.Enum
    public static enum AplicacionOferta {
        POR_CANTIDAD,
        POR_IMPORTE,
        SIEMPRE
    }

    @ProtoFileV2.File.Enum
    public static enum VisualizacionOferta {
        VISIBLE,
        INVISIBLE
    }

    @ProtoFileV2.File.Message
    public static class Oferta {

        @ProtoFileV2.File.Message.Field
        private AplicacionOferta aplicacion;
        @ProtoFileV2.File.Message.Field
        private VisualizacionOferta visible;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double valorAplicacion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double modificarPVP;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double modificarDTO;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Articulo articuloRegalo;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double cantidadRegalo;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String idAnuncioEnlazar;

        public AplicacionOferta getAplicacion() {
            return aplicacion;
        }

        public void setAplicacion(AplicacionOferta aplicacion) {
            this.aplicacion = aplicacion;
        }

        public VisualizacionOferta getVisible() {
            return visible;
        }

        public void setVisible(VisualizacionOferta visible) {
            this.visible = visible;
        }

        public double getValorAplicacion() {
            return valorAplicacion;
        }

        public void setValorAplicacion(double valorAplicacion) {
            this.valorAplicacion = valorAplicacion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getModificarPVP() {
            return modificarPVP;
        }

        public void setModificarPVP(double modificarPVP) {
            this.modificarPVP = modificarPVP;
        }

        public double getModificarDTO() {
            return modificarDTO;
        }

        public void setModificarDTO(double modificarDTO) {
            this.modificarDTO = modificarDTO;
        }

        public Articulo getArticuloRegalo() {
            return articuloRegalo;
        }

        public void setArticuloRegalo(Articulo articuloRegalo) {
            this.articuloRegalo = articuloRegalo;
        }

        public double getCantidadRegalo() {
            return cantidadRegalo;
        }

        public void setCantidadRegalo(double cantidadRegalo) {
            this.cantidadRegalo = cantidadRegalo;
        }

        public String getIdAnuncioEnlazar() {
            return idAnuncioEnlazar;
        }

        public void setIdAnuncioEnlazar(String idAnuncioEnlazar) {
            this.idAnuncioEnlazar = idAnuncioEnlazar;
        }

    }

    @ProtoFileV2.File.Message
    public static class Stock {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String sucursalId;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String ubicacion;
        @ProtoFileV2.File.Message.Field
        private double stock;
        @ProtoFileV2.File.Message.Field
        private String stockMostrar;

        public String getSucursalId() {
            return sucursalId;
        }

        public void setSucursalId(String sucursalId) {
            this.sucursalId = sucursalId;
        }

        public String getUbicacion() {
            return ubicacion;
        }

        public void setUbicacion(String ubicacion) {
            this.ubicacion = ubicacion;
        }

        public double getStock() {
            return stock;
        }

        public void setStock(double stock) {
            this.stock = stock;
        }

        public String getStockMostrar() {
            return stockMostrar;
        }

        public void setStockMostrar(String stockMostrar) {
            this.stockMostrar = stockMostrar;
        }

    }

    @ProtoFileV2.File.Message
    public static class Tasa {

        @ProtoFileV2.File.Message.Field
        private String id;
        @ProtoFileV2.File.Message.Field
        private String codigo;
        @ProtoFileV2.File.Message.Field
        private double importe;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

    }

    @ProtoFileV2.File.Message
    public static class Restricciones {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double minimoVenta;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double moduloVenta;

        public double getMinimoVenta() {
            return minimoVenta;
        }

        public void setMinimoVenta(double minimoVenta) {
            this.minimoVenta = minimoVenta;
        }

        public double getModuloVenta() {
            return moduloVenta;
        }

        public void setModuloVenta(double moduloVenta) {
            this.moduloVenta = moduloVenta;
        }

    }

    @ProtoFileV2.File.Message
    public static class Kit {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Articulo articuloKit;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Articulo> componenteKit;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Double> cantidadComponente;

        public Articulo getArticuloKit() {
            return articuloKit;
        }

        public void setArticuloKit(Articulo articuloKit) {
            this.articuloKit = articuloKit;
        }

        public List<Articulo> getComponenteKit() {
            return componenteKit;
        }

        public void setComponenteKit(List<Articulo> componenteKit) {
            this.componenteKit = componenteKit;
        }

        public List<Double> getCantidadComponente() {
            return cantidadComponente;
        }

        public void setCantidadComponente(List<Double> cantidadComponente) {
            this.cantidadComponente = cantidadComponente;
        }

    }

    @ProtoFileV2.File.Message
    public static class Articulo {

        @ProtoFileV2.File.Message.Field
        private String articuloId;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Identificacion identificacion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Stock stock;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Stock> stocks;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Precio> precios;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Referencia referenciaPrincipal;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Referencia> referencias;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Marca marca;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Articulo> equivalentes;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Articulo cascoEnvase;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private Restricciones restricciones;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Tasa> tasas;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Kit> kits;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<Oferta> ofertas;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private boolean generico;

        public String getArticuloId() {
            return articuloId;
        }

        public void setArticuloId(String articuloId) {
            this.articuloId = articuloId;
        }

        public Identificacion getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(Identificacion identificacion) {
            this.identificacion = identificacion;
        }

        public Stock getStock() {
            return stock;
        }

        public void setStock(Stock stock) {
            this.stock = stock;
        }

        public List<Stock> getStocks() {
            return stocks;
        }

        public void setStocks(List<Stock> stocks) {
            this.stocks = stocks;
        }

        public List<Precio> getPrecios() {
            return precios;
        }

        public void setPrecios(List<Precio> precios) {
            this.precios = precios;
        }

        public Referencia getReferenciaPrincipal() {
            return referenciaPrincipal;
        }

        public void setReferenciaPrincipal(Referencia referenciaPrincipal) {
            this.referenciaPrincipal = referenciaPrincipal;
        }

        public List<Referencia> getReferencias() {
            return referencias;
        }

        public void setReferencias(List<Referencia> referencias) {
            this.referencias = referencias;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Marca getMarca() {
            return marca;
        }

        public void setMarca(Marca marca) {
            this.marca = marca;
        }

        public List<Articulo> getEquivalentes() {
            return equivalentes;
        }

        public void setEquivalentes(List<Articulo> equivalentes) {
            this.equivalentes = equivalentes;
        }

        public Articulo getCascoEnvase() {
            return cascoEnvase;
        }

        public void setCascoEnvase(Articulo cascoEnvase) {
            this.cascoEnvase = cascoEnvase;
        }

        public Restricciones getRestricciones() {
            return restricciones;
        }

        public void setRestricciones(Restricciones restricciones) {
            this.restricciones = restricciones;
        }

        public List<Tasa> getTasas() {
            return tasas;
        }

        public void setTasas(List<Tasa> tasas) {
            this.tasas = tasas;
        }

        public List<Kit> getKits() {
            return kits;
        }

        public void setKits(List<Kit> kits) {
            this.kits = kits;
        }

        public List<Oferta> getOfertas() {
            return ofertas;
        }

        public void setOfertas(List<Oferta> ofertas) {
            this.ofertas = ofertas;
        }

        public boolean isGenerico() {
            return generico;
        }

        public void setGenerico(boolean generico) {
            this.generico = generico;
        }

    }

    @ProtoFileV2.File.Message
    public static class Direccion {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String direccion1;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String direccion2;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String numero;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String escalera;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String piso;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String puerta;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String codigoPostal;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String poblacion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String provincia;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String pais;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String nif;

        public String getDireccion1() {
            return direccion1;
        }

        public void setDireccion1(String direccion1) {
            this.direccion1 = direccion1;
        }

        public String getDireccion2() {
            return direccion2;
        }

        public void setDireccion2(String direccion2) {
            this.direccion2 = direccion2;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getEscalera() {
            return escalera;
        }

        public void setEscalera(String escalera) {
            this.escalera = escalera;
        }

        public String getPiso() {
            return piso;
        }

        public void setPiso(String piso) {
            this.piso = piso;
        }

        public String getPuerta() {
            return puerta;
        }

        public void setPuerta(String puerta) {
            this.puerta = puerta;
        }

        public String getCodigoPostal() {
            return codigoPostal;
        }

        public void setCodigoPostal(String codigoPostal) {
            this.codigoPostal = codigoPostal;
        }

        public String getPoblacion() {
            return poblacion;
        }

        public void setPoblacion(String poblacion) {
            this.poblacion = poblacion;
        }

        public String getProvincia() {
            return provincia;
        }

        public void setProvincia(String provincia) {
            this.provincia = provincia;
        }

        public String getPais() {
            return pais;
        }

        public void setPais(String pais) {
            this.pais = pais;
        }

        public String getNif() {
            return nif;
        }

        public void setNif(String nif) {
            this.nif = nif;
        }

    }

    @ProtoFileV2.File.Enum
    public static enum TipoArchivo {
        FACTURA,
        ANUNCIO,
        ALBARAN,
        OFERTA,
        OTROS
    }

    @ProtoFileV2.File.Message
    public static class Archivo {

        @ProtoFileV2.File.Message.Field
        private String idArchivo;
        @ProtoFileV2.File.Message.Field
        private TipoArchivo tipo;

        public String getIdArchivo() {
            return idArchivo;
        }

        public void setIdArchivo(String idArchivo) {
            this.idArchivo = idArchivo;
        }

        public TipoArchivo getTipo() {
            return tipo;
        }

        public void setTipo(TipoArchivo tipo) {
            this.tipo = tipo;
        }

    }

    @ProtoFileV2.File.Message
    public static class DatosTaller {

        @ProtoFileV2.File.Message.Field
        private String nombreComercial;
        @ProtoFileV2.File.Message.Field
        private Direccion direccion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String telefono;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String movil;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String email;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double precioHora;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private double porcentajeImpuesto;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String tituloImpuesto;

        public String getNombreComercial() {
            return nombreComercial;
        }

        public void setNombreComercial(String nombreComercial) {
            this.nombreComercial = nombreComercial;
        }

        public Direccion getDireccion() {
            return direccion;
        }

        public void setDireccion(Direccion direccion) {
            this.direccion = direccion;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public String getMovil() {
            return movil;
        }

        public void setMovil(String movil) {
            this.movil = movil;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getPrecioHora() {
            return precioHora;
        }

        public void setPrecioHora(double precioHora) {
            this.precioHora = precioHora;
        }

        public double getPorcentajeImpuesto() {
            return porcentajeImpuesto;
        }

        public void setPorcentajeImpuesto(double porcentajeImpuesto) {
            this.porcentajeImpuesto = porcentajeImpuesto;
        }

        public String getTituloImpuesto() {
            return tituloImpuesto;
        }

        public void setTituloImpuesto(String tituloImpuesto) {
            this.tituloImpuesto = tituloImpuesto;
        }

    }

    @ProtoFileV2.File.Enum
    public static enum TipoLogin {
        POR_WEB,
        POR_PLUGIN
    }
}
