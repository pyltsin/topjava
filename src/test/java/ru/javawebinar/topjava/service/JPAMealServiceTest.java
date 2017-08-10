package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.slf4j.Logger;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.loghelper.LogHelper;
import ru.javawebinar.topjava.loghelper.StopwatchImpl;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Pyltsin on 10.08.2017.
 */
@ActiveProfiles(Profiles.JPA)
public class JPAMealServiceTest extends MealServiceTConcrete {

    private static final Logger resultLog = getLogger("result");

    private static StringBuilder results = new StringBuilder();
    @Rule
    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    public Stopwatch stopwatch = new StopwatchImpl(resultLog, results);

    @AfterClass
    public static void printResult() {
        LogHelper.logPrint(resultLog, results);
    }
}
