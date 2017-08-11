package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.loghelper.LogHelper;
import ru.javawebinar.topjava.loghelper.StopwatchImpl;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAUserServiceTest extends UserServiceTConcrete {

    private static final Logger resultLog = getLogger("result");

    private static StringBuilder results = new StringBuilder();
    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new StopwatchImpl(resultLog, results);

    @AfterClass
    public static void printResult() {
        LogHelper.logPrint(resultLog, results);
    }

    @Test
    public void testGetWithMeals() throws Exception {
        User user = service.getWithMeal(USER_ID);
        MATCHER.assertEquals(USER, user);
        MealTestData.MATCHER.assertCollectionEquals(Arrays.asList(MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1), user.getMeals());
    }
}
