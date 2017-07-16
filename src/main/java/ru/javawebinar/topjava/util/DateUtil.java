package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Pyltsin on 16.07.2017.
 */
public final class DateUtil{
    private DateUtil() {}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatToTable(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, "yyyy/MM/dd HH:mm:ss");
    }
}