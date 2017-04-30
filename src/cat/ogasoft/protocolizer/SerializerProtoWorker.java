package cat.ogasoft.protocolizer;

/**
 *
 * @author Oscar Galera i Alfaro
 */
public interface SerializerProtoWorker<T> {

    public void work(T container);

    public byte[] waitUntilEnd() throws InterruptedException;
}
