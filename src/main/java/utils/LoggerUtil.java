package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static Logger logger;

    public static void initLogger() {
        logger = LogManager.getLogger("AutomationLogger");
    }

    public static Logger getLogger() {
        return logger;
    }
}
