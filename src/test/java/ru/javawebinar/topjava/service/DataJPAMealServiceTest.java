package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.loghelper.LogHelper;
import ru.javawebinar.topjava.loghelper.StopwatchImpl;
import ru.javawebinar.topjava.model.Meal;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@ActiveProfiles(Profiles.DATAJPA)
public class DataJPAMealServiceTest extends MealServiceTConcrete {

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
    public void testGetWithUser() throws Exception {
        Meal actual = service.getWithUser(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL1, actual);
        UserTestData.MATCHER.assertEquals(ADMIN, actual.getUser());
    }
}
