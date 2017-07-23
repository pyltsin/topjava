package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(this::save);
    }


    @Override
    public Meal save(Meal meal) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        repository.put(meal.getId(), meal);
        return meal;

    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {}, user{}", id, userId);

        Meal meal = repository.get(id);
        if (meal != null && meal.getUserId() == userId) {
            return repository.remove(id) != null;
        }
        return false;

    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {} , user{}", id, userId);

        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId ?
                meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll user{}", userId);
        return repository.values().stream().
                filter(meal -> meal.getUserId() == userId).
                sorted(Comparator.comparing(Meal::getDateTime)).
                collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetweenDate(int userId, LocalDate startDate, LocalDate endDate) {
        return getAll(userId).stream().
                filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate)).
                collect(Collectors.toList());
    }

    @Override
    public Meal update(Meal meal, int userId) {
        if (get(meal.getId(), userId) == null) {
            return null;
        }
        return repository.put(meal.getId(), meal);

    }
}

