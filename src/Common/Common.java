package Common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sayarasin on 08/01/2016.
 */
public class Common {

    public static String getTimeStamp(String msg) {
        return "Timestamp:"
                + new SimpleDateFormat("yyyyMMddHHmmssSSS")
                    .format(new Date()).toString()
                + ((msg == null || msg.isEmpty()) ? "" : msg);
    }
}
