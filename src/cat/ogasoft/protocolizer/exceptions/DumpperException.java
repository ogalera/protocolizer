/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.ogasoft.protocolizer.exceptions;

/**
 * @author Oscar Galera i Alfaro
 * @date Apr 27, 2017 [5:14:45 PM]
 *
 * @brief DESCRIPTION
 */
public class DumpperException extends Exception {

    public DumpperException(String message) {
        super("Exception in Serialization phase, message: " + message);
    }

}
