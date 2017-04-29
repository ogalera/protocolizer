package cat.ogasoft.protocolizer.exceptions;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [4:37:11 PM]
 *
 * @brief DESCRIPTION
 */
public class GenerationException extends Exception {

    public GenerationException(String message) {
        super("Exception in Generation phase, message: " + message);
    }

}
