package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {
    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) {
        checkNew(meal);
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    @Override
    public Meal get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    /**
     * meal without userId!
     */
    @Override
    public Meal update(Meal meal, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.update(meal, userId), meal.getId());
    }

    @Override
    public List<MealWithExceed> getAll(int userId, int calories) {
        return MealsUtil.getWithExceeded(repository.getAll(userId), calories);
    }

    @Override
    public List<MealWithExceed> getAllBetweenDateTime(int userId, int calories, LocalDate startDate, LocalDate endDate,
                                                      LocalTime startTime, LocalTime endTime) {
        return MealsUtil.getFilteredWithExceeded(repository.getBetweenDate(userId, startDate, endDate),
                startTime, endTime, calories);
    }

    @Override
    public List<MealWithExceed> getAllBetweenDateTime(int userId, int calories, String startDateStr, String endDateStr,
                                                      String startTimeStr, String endTimeStr) throws DateTimeParseException {

        LocalDate startDate = startDateStr != null && !startDateStr.equals("") ? LocalDate.parse(startDateStr) : LocalDate.MIN;
        LocalDate endDate = endDateStr != null && !endDateStr.equals("") ? LocalDate.parse(endDateStr) : LocalDate.MAX;

        LocalTime startTime = startTimeStr != null && !startTimeStr.equals("") ? LocalTime.parse(startTimeStr) : LocalTime.MIN;
        LocalTime endTime = endTimeStr != null && !endTimeStr.equals("") ? LocalTime.parse(endTimeStr) : LocalTime.MAX;

        return getAllBetweenDateTime(userId, calories, startDate, endDate, startTime, endTime);
    }

}