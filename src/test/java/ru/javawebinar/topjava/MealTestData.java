package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public class MealTestData {

    public static final int MEAL_ID_START = 100002;

    public static final Meal MEAL_USER = new Meal(MEAL_ID_START, LocalDateTime.parse("2017-07-29T06:34:20"),
            "ужин", 1000);

    public static final Meal MEAL_USER2 = new Meal(MEAL_ID_START + 1, LocalDateTime.parse("2017-07-29T07:34:20"),
            "ужин2", 1200);

    public static final Meal MEAL_ADMIN = new Meal(MEAL_ID_START + 2, LocalDateTime.parse("2017-07-29T06:34:25"),
            "завтрак", 1020);

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();
}

