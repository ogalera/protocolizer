package cat.ogasoft.protocolizer.test;

import cat.ogasoft.protocolizer.annotations.ProtoFileV2;
import cat.ogasoft.protocolizer.annotations.ProtoFileV2.File.Message.Field.DataType;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 21, 2017 [3:01:14 PM]
 *
 * @brief DESCRIPTION
 */
@ProtoFileV2.File(name = "File", generateSource = true)
@ProtoFileV2.Serialize
public class Test2 {

    @ProtoFileV2.File.Message
    public static class SearchRequest {

        @ProtoFileV2.File.Message.Field(type = DataType.STRING)
        private String query;

        @ProtoFileV2.File.Message.Field
        private int page_number;

        @ProtoFileV2.File.Message.Field
        private int result_per_page;

        @ProtoFileV2.File.Enum
        public static enum Corpus {
            UNIVERSAL,
            WEB,
            IMAGES,
            LOCAL,
            NEWS,
            PRODUCTS,
            VIDEO;
        }

        @ProtoFileV2.File.Message.Field(type = DataType.COMPOSED)
        private Corpus corpussdfa;
    }
}
