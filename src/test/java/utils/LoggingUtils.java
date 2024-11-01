package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingUtils {

    // Use the class name for the logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingUtils.class);

    // Method to log info messages
    public static void logInfo(String message) {
        LOGGER.info(message);
    }

    // Method to log error messages
    public static void logError(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
