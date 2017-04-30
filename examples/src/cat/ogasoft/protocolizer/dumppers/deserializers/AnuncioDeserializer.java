//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.deserializers;


public class AnuncioDeserializer {
    public static class AnuncioReq {
        public cat.ogasoft.examples.Anuncio.AnuncioReq dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            com.ad.connector.protobuf.Anuncio.AnuncioReq result = com.ad.connector.protobuf.Anuncio.AnuncioReq.parseFrom(target);
            return buildAnuncioReq(result);
        }
        public static cat.ogasoft.examples.Anuncio.AnuncioReq buildAnuncioReq(com.ad.connector.protobuf.Anuncio.AnuncioReq target){
            cat.ogasoft.examples.Anuncio.AnuncioReq result = new cat.ogasoft.examples.Anuncio.AnuncioReq();
            result.setIdAnuncio(target.getIdAnuncio());
            return result;
        }
    }

    public static class DefinicionAnuncio {
        public cat.ogasoft.examples.Anuncio.DefinicionAnuncio dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            com.ad.connector.protobuf.Anuncio.DefinicionAnuncio result = com.ad.connector.protobuf.Anuncio.DefinicionAnuncio.parseFrom(target);
            return buildDefinicionAnuncio(result);
        }
        public static cat.ogasoft.examples.Anuncio.DefinicionAnuncio buildDefinicionAnuncio(com.ad.connector.protobuf.Anuncio.DefinicionAnuncio target){
            cat.ogasoft.examples.Anuncio.DefinicionAnuncio result = new cat.ogasoft.examples.Anuncio.DefinicionAnuncio();
            result.setIdioma(target.getIdioma());
            result.setTipoAnuncio(cat.ogasoft.examples.Anuncio.TipoAnuncio.valueOf(target.getTipoAnuncio().name()));
            result.setVerCuadro(cat.ogasoft.examples.Anuncio.VerCuadro.valueOf(target.getVerCuadro().name()));
            if(target.hasTitulo()){
                result.setTitulo(target.getTitulo());
            }
            if(target.hasDescripcion()){
                result.setDescripcion(target.getDescripcion());
            }
            if(target.hasDescripcionHTML()){
                result.setDescripcionHTML(target.getDescripcionHTML());
            }
            if(target.hasImagen()){
                result.setImagen(target.getImagen());
            }
            if(target.hasLinkHTML()){
                result.setLinkHTML(target.getLinkHTML());
            }
            return result;
        }
    }

    public static class AnuncioRes {
        public cat.ogasoft.examples.Anuncio.AnuncioRes dump(byte[] target) throws com.google.protobuf.InvalidProtocolBufferException {
            com.ad.connector.protobuf.Anuncio.AnuncioRes result = com.ad.connector.protobuf.Anuncio.AnuncioRes.parseFrom(target);
            return buildAnuncioRes(result);
        }
        public static cat.ogasoft.examples.Anuncio.AnuncioRes buildAnuncioRes(com.ad.connector.protobuf.Anuncio.AnuncioRes target){
            cat.ogasoft.examples.Anuncio.AnuncioRes result = new cat.ogasoft.examples.Anuncio.AnuncioRes();
            if(target.getAnunciosList() != null){
                java.util.ArrayList<cat.ogasoft.examples.Anuncio.DefinicionAnuncio> r = new java.util.ArrayList<>(target.getAnunciosCount());
                for(com.ad.connector.protobuf.Anuncio.DefinicionAnuncio k:target.getAnunciosList()){
                    r.add(cat.ogasoft.protocolizer.dumppers.deserializers.AnuncioDeserializer.DefinicionAnuncio.buildDefinicionAnuncio(k));
                }
                result.setAnuncios(r);
            }
            return result;
        }
    }

}
