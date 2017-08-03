package ru.javawebinar.topjava.rules;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.HashMap;

/**
 * Created by Pyltsin on 03.08.2017.
 */
public class TestWatcherImpl extends TestWatcher {
    private long time;
    private HashMap<String, Long> timeTest;

    public TestWatcherImpl(HashMap<String, Long> timeTest) {
        this.timeTest = timeTest;
    }

    @Override
    protected void starting(Description description) {
        super.starting(description);
        time = System.currentTimeMillis();

    }

    @Override
    protected void finished(Description description) {
        super.finished(description);
        long end = getTime();
        timeTest.put(description.getMethodName(), end);
        System.out.printf("For test %s time %d ms\n",
                description.getMethodName(), end);
    }

    private long getTime() {
        return (-time + System.currentTimeMillis());
    }

    public void printStatistics() {
        for (String s : timeTest.keySet()) {
            System.out.println(s + " " + timeTest.get(s));
        }
    }
}
