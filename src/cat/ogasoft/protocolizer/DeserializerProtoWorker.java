package cat.ogasoft.protocolizer;

import cat.ogasoft.protocolizer.exceptions.DeserializationException;

/**
 *
 * @author Oscar Galera i Alfaro
 */
public interface DeserializerProtoWorker<T> {

    public void work(byte[] data);

    public T waitUntilEnd() throws InterruptedException, DeserializationException;
}
