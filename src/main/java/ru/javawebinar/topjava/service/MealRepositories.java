package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pyltsin on 16.07.2017.
 */
public class MealRepositories implements Service<Meal> {
    List<Meal> listMeals = new ArrayList<>();

    public MealRepositories() {
        this.listMeals = Arrays.asList(
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), 1, "Завтрак", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), 2, "Обед", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), 3, "Ужин", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), 4, "Завтрак", 1000),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), 5, "Обед", 500),
                new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), 6, "Ужин", 510));

    }

    public List<Meal> getAll() {
        return listMeals;
    }

    public void add(Meal meal) {
        listMeals.add(meal);
    }

    public void delete(Meal meal) {

    }


    public void update(Meal meal) {

    }
}
