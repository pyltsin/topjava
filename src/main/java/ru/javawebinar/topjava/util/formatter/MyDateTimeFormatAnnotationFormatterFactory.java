package ru.javawebinar.topjava.util.formatter;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pyltsin on 03.09.2017.
 */
public class MyDateTimeFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<MyDateTime> {

    private static final Set<Class<?>> FIELD_TYPES;

    static {
        Set<Class<?>> fieldTypes = new HashSet<Class<?>>(2);
        fieldTypes.add(LocalTime.class);
        fieldTypes.add(LocalDate.class);
        FIELD_TYPES = Collections.unmodifiableSet(fieldTypes);
    }


    @Override
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(MyDateTime annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(MyDateTime annotation, Class<?> fieldType) {
        return getFormatter(annotation, fieldType);
    }


    private Formatter<?> getFormatter(MyDateTime annotation, Class<?> fieldType) {
        switch (annotation.iso()) {
            case DATE:
                return new MyDateFormatter();
            case TIME:
                return new MyTimeFormatter();
        }
        return null;
    }
}
