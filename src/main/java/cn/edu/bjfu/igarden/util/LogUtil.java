package cn.edu.bjfu.igarden.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static Logger logger = LoggerFactory.getLogger(LogUtil.class);

    /**
     * debug
     * @param text content
     */
    public static void d(String text) {
        logger.debug(text);
    }

    /**
     * info
     * @param text content
     */
    public static void i(String text) {
        logger.info(text);
    }

    /**
     * warning
     * @param text content
     */
    public static void w(String text) {
        logger.warn(text);
    }

    /**
     * error
     * @param text content
     */
    public static void e(String text) {
        logger.error(text);
    }
}
