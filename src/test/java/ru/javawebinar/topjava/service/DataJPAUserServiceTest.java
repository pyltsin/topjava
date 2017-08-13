package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.HashSet;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAUserServiceTest extends UserServiceTConcrete {

    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeal(USER_ID);
        UserTestData.MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(new HashSet<>(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1)),
                new HashSet<>(user.getMeals()));
    }

    @Test
    public void testGetWithNullMeals() throws Exception {
        User user = service.getWithMeal(USER2.getId());
        UserTestData.MATCHER.assertEquals(USER2, user);
        Assert.assertEquals(0, user.getMeals().size());
    }

    @Test
    public void testGetWithMealsNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        User user = service.getWithMeal(-1);
    }
}
