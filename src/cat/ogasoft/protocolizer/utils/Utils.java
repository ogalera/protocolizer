package cat.ogasoft.protocolizer.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Oscar Galera i Alfaro
 * @date   Mar 19, 2017 [6:22:07 PM]
 */
public abstract class Utils {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat DATE_HOUR_FORMAT = new SimpleDateFormat("dd/MM/yyyy hh:mm");
    
    public static String getDate(){
        return DATE_FORMAT.format(new Date());
    }
    
    public static String getDateTime(){
        return DATE_HOUR_FORMAT.format(new Date());
    }
}
