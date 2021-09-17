package unibl.etf.pisio.trelloproject.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggingUtil {

    public static void logException(Throwable ex, Log log) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ex);
        stringBuilder.append(System.lineSeparator());
        for (StackTraceElement element : ex.getStackTrace()) {
            stringBuilder.append(element);
            stringBuilder.append(System.lineSeparator());
        }
        log.error(stringBuilder);
    }

    public static <T> void logException(Throwable ex, Class<T> clazz) {
        logException(ex, LogFactory.getLog(clazz));
    }
}
