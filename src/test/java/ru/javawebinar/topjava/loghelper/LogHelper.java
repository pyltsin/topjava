package ru.javawebinar.topjava.loghelper;

import org.slf4j.Logger;

/**
 * Created by Pyltsin on 10.08.2017.
 */
public class LogHelper {
    public static void logPrint(Logger resultLog, StringBuilder results) {
        resultLog.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------\n" +
                results +
                "---------------------------------\n");
    }
}
