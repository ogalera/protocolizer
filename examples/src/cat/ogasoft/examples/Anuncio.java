/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.examples;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import java.util.List;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [10:47:32 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(pJavaName = "Anuncio", pJavaPackage = "com.ad.connector.protobuf", generateSource = true)
@ProtoFileV2.Dumpper
public class Anuncio {

    @ProtoFileV2.File.Message
    public static class AnuncioReq {

        @ProtoFileV2.File.Message.Field
        private String idAnuncio;

        public String getIdAnuncio() {
            return idAnuncio;
        }

        public void setIdAnuncio(String idAnuncio) {
            this.idAnuncio = idAnuncio;
        }

    }

    @ProtoFileV2.File.Enum
    public static enum TipoAnuncio {
        POPUP,
        EN_MARCO,
        PANTALLA_COMPLETA;
    }

    @ProtoFileV2.File.Enum
    public static enum VerCuadro {
        SE_IDENTIFICA;
    }
//

    @ProtoFileV2.File.Message
    public static class DefinicionAnuncio {

        @ProtoFileV2.File.Message.Field
        private String idioma;
        @ProtoFileV2.File.Message.Field
        private TipoAnuncio tipoAnuncio;
        @ProtoFileV2.File.Message.Field
        private VerCuadro verCuadro;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String titulo;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String descripcion;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private boolean descripcionHTML;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String imagen;
        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.OPTIONAL)
        private String linkHTML;

        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }

        public TipoAnuncio getTipoAnuncio() {
            return tipoAnuncio;
        }

        public void setTipoAnuncio(TipoAnuncio tipoAnuncio) {
            this.tipoAnuncio = tipoAnuncio;
        }

        public VerCuadro getVerCuadro() {
            return verCuadro;
        }

        public void setVerCuadro(VerCuadro verCuadro) {
            this.verCuadro = verCuadro;
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

        public boolean isDescripcionHTML() {
            return descripcionHTML;
        }

        public void setDescripcionHTML(boolean descripcionHTML) {
            this.descripcionHTML = descripcionHTML;
        }

        public String getImagen() {
            return imagen;
        }

        public void setImagen(String imagen) {
            this.imagen = imagen;
        }

        public String getLinkHTML() {
            return linkHTML;
        }

        public void setLinkHTML(String linkHTML) {
            this.linkHTML = linkHTML;
        }

    }

    @ProtoFileV2.File.Message
    public static class AnuncioRes {

        @ProtoFileV2.File.Message.Field(label = ProtoFileV2.File.Message.Field.Label.REPEATED)
        private List<DefinicionAnuncio> anuncios;

        public List<DefinicionAnuncio> getAnuncios() {
            return anuncios;
        }

        public void setAnuncios(List<DefinicionAnuncio> anuncios) {
            this.anuncios = anuncios;
        }

    }
}
