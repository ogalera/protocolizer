package cat.ogasoft.protocolizer.pens.dumppers;

/**
 * @author Oscar Galera i Alfaro
 * @date   Apr 30, 2017 [3:27:18 PM]
 *
 * @brief DESCRIPTION
 */
public class DumppersFilePen {

    private DumpperFilePen serializer;
    private DumpperFilePen deserializer;

    public DumpperFilePen getSerializer() {
        return serializer;
    }

    public void setSerializer(DumpperFilePen serializer) {
        this.serializer = serializer;
    }

    public DumpperFilePen getDeserializer() {
        return deserializer;
    }

    public void setDeserializer(DumpperFilePen deserializer) {
        this.deserializer = deserializer;
    }

    public boolean hasSerializer() {
        return serializer != null;
    }

    public boolean hasDeserializer() {
        return deserializer != null;
    }

}
