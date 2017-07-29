package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"})
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MealTestData.MEAL_ID_START, UserTestData.USER_ID);
        MATCHER.assertEquals(MEAL_USER, meal);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1, 1);
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(MealTestData.MEAL_ID_START, UserTestData.USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Collections.singletonList(MEAL_USER2), service.getAll(UserTestData.USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteNotFound() throws Exception {
        service.delete(1, 1);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Meal> all = service.getAll(USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL_USER2, MEAL_USER), all);
    }


    @Test
    public void testGetBetweenDateTimes() throws Exception {
        Collection<Meal> all = service.getBetweenDateTimes(LocalDateTime.parse("2017-07-29T07:34:19"), LocalDateTime.parse("2017-07-29T07:34:21"), USER_ID);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL_USER2), all);

    }


    @Test
    public void testUpdate() throws Exception {
        Meal fromDB = service.get(MEAL_USER.getId(), USER_ID);
        fromDB.setCalories(9);
        service.update(fromDB, USER_ID);
        MATCHER.assertEquals(service.get(MEAL_USER.getId(), USER_ID), fromDB);
    }

    @Test
    public void testSave() throws Exception {
        Meal mealNew = new Meal(LocalDateTime.now(), "test", 1000);
        Meal mealFromDB = service.save(mealNew, USER_ID);
        MATCHER.assertEquals(service.get(mealFromDB.getId(), USER_ID), mealNew);
    }

    @Test(expected = NotFoundException.class)
    public void testFailedUpdate() throws Exception {
        Meal fromDB = service.get(MEAL_USER.getId(), USER_ID);
        service.update(fromDB, USER_ID + 1);
    }

    @Test(expected = NotFoundException.class)
    public void testFailedGet() throws Exception {
        Meal meal = service.get(MealTestData.MEAL_ID_START, UserTestData.USER_ID + 1);
    }

    @Test(expected = NotFoundException.class)
    public void testFailedDelete() throws Exception {
        service.delete(MealTestData.MEAL_ID_START, UserTestData.USER_ID + 1);
    }

}