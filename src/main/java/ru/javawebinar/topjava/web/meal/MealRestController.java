package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.checkIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.save(meal);

    }

    public void delete(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();
        log.info("delete {},  user {}", id, userId);
        try {
            service.delete(id, userId);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    public Meal get(int id) throws NotFoundException {
        int userId = AuthorizedUser.id();

        log.info("get {} user{}", id, userId);
        return service.get(id, userId);
    }

    public void update(Meal meal, int id) throws NotFoundException {
        int userId = AuthorizedUser.id();

        log.info("update {} id {} user{}", meal, id, userId);
        checkIdConsistent(meal, id);
        service.update(meal, userId);
    }

    public List<MealWithExceed> getAll(int calories) {
        int userId = AuthorizedUser.id();

        log.info("getAll userid {}, calories {}", userId, calories);
        return service.getAll(userId, calories);
    }

    public List<MealWithExceed> getAllBetweenDateTime(int calories, LocalDate startDate, LocalDate endDate,
                                                      LocalTime startTime, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getAll userid {}, calories {} date {} - {}  time {} - {}", userId, calories, startDate, endDate,
                startTime, endTime);
        return service.getAllBetweenDateTime(userId, calories, startDate, endDate,
                startTime, endTime);
    }

    public List<MealWithExceed> getAllBetweenDateTime(int calories, String startDate, String endDate,
                                                      String startTime, String endTime) throws ParseException {
        int userId = AuthorizedUser.id();
        log.info("getAll userid {}, calories {} date {} - {}  time {} - {}", userId, calories, startDate, endDate,
                startTime, endTime);
        return service.getAllBetweenDateTime(userId, calories, startDate, endDate,
                startTime, endTime);
    }
}
