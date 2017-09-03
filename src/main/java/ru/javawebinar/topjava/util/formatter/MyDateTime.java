package ru.javawebinar.topjava.util.formatter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Pyltsin on 03.09.2017.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyDateTime {
    ISO iso() default ISO.DATE;
    enum ISO {

        DATE,

        TIME}
}
