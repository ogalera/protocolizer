package cat.ogasoft.protocolizer.test;

import cat.ogasoft.protocolizer.annotations.Dumpper;
import cat.ogasoft.protocolizer.utils.DataType;
import cat.ogasoft.protocolizer.annotations.Message;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date   Mar 19, 2017 [2:33:47 PM]
 */
@Message()
@Dumpper
public class Test1 {
    @Message.Field(optional = true, type = DataType.BOOL)
    private String a;
}
