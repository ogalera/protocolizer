//Protocolizer 01/05/2017 24:24:27
//This class has been generated automatically, plase
//DO NOT EDIT!
//
//For any question, feel free to contact me at: oscar.galeraa@gmail.com

package cat.ogasoft.protocolizer.dumppers.serializers;


public class AnuncioSerializer {
    public static class AnuncioReq {
        public byte[] dump(cat.ogasoft.examples.Anuncio.AnuncioReq target){
            return buildAnuncioReq(target).toByteArray();
        }
        public static com.ad.connector.protobuf.Anuncio.AnuncioReq buildAnuncioReq(cat.ogasoft.examples.Anuncio.AnuncioReq target){
            com.ad.connector.protobuf.Anuncio.AnuncioReq.Builder builder = com.ad.connector.protobuf.Anuncio.AnuncioReq.newBuilder();
            builder.setIdAnuncio(target.getIdAnuncio());
            return builder.build();
        }
    }

    public static class DefinicionAnuncio {
        public byte[] dump(cat.ogasoft.examples.Anuncio.DefinicionAnuncio target){
            return buildDefinicionAnuncio(target).toByteArray();
        }
        public static com.ad.connector.protobuf.Anuncio.DefinicionAnuncio buildDefinicionAnuncio(cat.ogasoft.examples.Anuncio.DefinicionAnuncio target){
            com.ad.connector.protobuf.Anuncio.DefinicionAnuncio.Builder builder = com.ad.connector.protobuf.Anuncio.DefinicionAnuncio.newBuilder();
            builder.setIdioma(target.getIdioma());
            builder.setTipoAnuncio(com.ad.connector.protobuf.Anuncio.TipoAnuncio.valueOf(target.getTipoAnuncio().name()));
            builder.setVerCuadro(com.ad.connector.protobuf.Anuncio.VerCuadro.valueOf(target.getVerCuadro().name()));
            if(target.getTitulo() != null){
                builder.setTitulo(target.getTitulo());
            }
            if(target.getDescripcion() != null){
                builder.setDescripcion(target.getDescripcion());
            }
            if(target.isDescripcionHTML() ){
                builder.setDescripcionHTML(target.isDescripcionHTML());
            }
            if(target.getImagen() != null){
                builder.setImagen(target.getImagen());
            }
            if(target.getLinkHTML() != null){
                builder.setLinkHTML(target.getLinkHTML());
            }
            return builder.build();
        }
    }

    public static class AnuncioRes {
        public byte[] dump(cat.ogasoft.examples.Anuncio.AnuncioRes target){
            return buildAnuncioRes(target).toByteArray();
        }
        public static com.ad.connector.protobuf.Anuncio.AnuncioRes buildAnuncioRes(cat.ogasoft.examples.Anuncio.AnuncioRes target){
            com.ad.connector.protobuf.Anuncio.AnuncioRes.Builder builder = com.ad.connector.protobuf.Anuncio.AnuncioRes.newBuilder();
            if(target.getAnuncios() != null){
                if(target.getAnuncios() != null){
                    for(cat.ogasoft.examples.Anuncio.DefinicionAnuncio k:target.getAnuncios()){
                        builder.addAnuncios(cat.ogasoft.protocolizer.dumppers.serializers.AnuncioSerializer.DefinicionAnuncio.buildDefinicionAnuncio(k));
                    }
                }
            }
            return builder.build();
        }
    }

}
