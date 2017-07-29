package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final Meal MEAL_USER = new Meal(100002, LocalDateTime.parse("2017-07-29 06:34:20"),
            "ужин", 1000);

    public static final Meal MEAL_USER2 = new Meal(100002, LocalDateTime.parse("2017-07-29 07:34:20"),
            "ужин2", 1200);

    public static final Meal MEAL_ADMIN = new Meal(100003, LocalDateTime.parse("2017-07-29 06:34:25"),
            "завтрак", 1020);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            (expected, actual) -> expected == actual ||
                    (Objects.equals(expected.getCalories(), actual.getCalories())
                            && Objects.equals(expected.getId(), actual.getId())
                            && Objects.equals(expected.getDateTime(), actual.getDateTime())
                            && Objects.equals(expected.getDescription(), actual.getDescription())
                    )
    );
}

