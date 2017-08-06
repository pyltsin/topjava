package ru.javawebinar.topjava.rules;

import org.junit.AssumptionViolatedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.HashMap;

public class StopWatcherImpl extends Stopwatch {
    private static final int NANOS_IN_MS = 1000_000;
    private HashMap<String, Long> timeTest;

    public StopWatcherImpl(HashMap<String, Long> timeTest) {
        this.timeTest = timeTest;
    }

    @Override
    protected void succeeded(long nanos, Description description) {
        putAndPrint(nanos, description);
    }

    private void putAndPrint(long nanos, Description description) {
        timeTest.put(description.getMethodName(), nanos / NANOS_IN_MS);
        System.out.printf("For test %s time %d ms\n",
                description.getMethodName(), nanos / NANOS_IN_MS);
    }

    @Override
    protected void failed(long nanos, Throwable e, Description description) {
        super.failed(nanos, e, description);
    }

    @Override
    protected void skipped(long nanos, AssumptionViolatedException e, Description description) {
        super.skipped(nanos, e, description);
    }

    @Override
    protected void finished(long nanos, Description description) {
        super.finished(nanos, description);
    }
}
