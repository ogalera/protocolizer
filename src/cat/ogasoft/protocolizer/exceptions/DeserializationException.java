/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.exceptions;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:14:53 PM]
 *
 * @brief DESCRIPTION
 */
public class DeserializationException extends Exception {

    public DeserializationException(String message) {
        super("Exception in Deserialization phase, message: "+message);
    }

}
