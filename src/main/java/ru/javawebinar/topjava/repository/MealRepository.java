package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository {
    Meal save(Meal Meal);

    // false if not found or not user
    boolean delete(int id, int userId);

    // null if not found or not user
    Meal get(int id, int userId);

    // null if not found or not user
    List<Meal> getAll(int userId);

    //null if not found or not user
    List<Meal> getBetweenDate(int userId, LocalDate startDate, LocalDate endDate);

    Meal update(Meal meal, int userId);
}
