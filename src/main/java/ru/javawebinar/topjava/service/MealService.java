package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MealService {
    Meal save(Meal meal);

    void delete(int id, int userId) throws NotFoundException;

    Meal get(int id, int userId) throws NotFoundException;

    Meal update(Meal meal, int userId) throws NotFoundException;

    List<MealWithExceed> getAll(int userId, int calories);

    List<MealWithExceed> getAllBetweenDate(int userId, int calories, LocalDate startDate, LocalDate endDate);

    List<MealWithExceed> getAllBetweenDateTime(int userId, int calories, LocalDateTime startDateTime, LocalDateTime endDateTime);
}