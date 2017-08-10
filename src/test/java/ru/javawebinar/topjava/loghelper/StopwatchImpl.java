package ru.javawebinar.topjava.loghelper;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * Created by Pyltsin on 10.08.2017.
 */
public class StopwatchImpl extends Stopwatch {
    private  final Logger resultLog ;

    private final  StringBuilder results;

    public StopwatchImpl(Logger resultLog, StringBuilder results) {
        this.resultLog = resultLog;
        this.results = results;
    }


    protected void finished(long nanos, Description description) {
        String result = String.format("%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
        results.append(result).append('\n');
        resultLog.info(result + " ms\n");
    }
}
