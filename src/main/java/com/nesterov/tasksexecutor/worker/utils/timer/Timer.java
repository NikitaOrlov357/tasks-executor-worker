package com.nesterov.tasksexecutor.worker.utils.timer;

public class Timer {
    private long start;
    private long finish;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        finish = System.currentTimeMillis();
    }

    public long getTime() {
        if (finish == 0) {
            throw new TimerErrorException("getTime started earlier than stop");
        }
        return finish - start;
    }

    public interface MethodWithoutParams {
        void doMethod();
    }


    public static long doAndGetTime(MethodWithoutParams methodWithoutParams, TimeUnit unit) {
        long start = System.currentTimeMillis();
        methodWithoutParams.doMethod();
        long finish = System.currentTimeMillis();
        return (finish - start) / generateValue(unit);
    }

    public static long generateValue(TimeUnit unit) {
        long value = 1;
        switch (unit) {
            case SECONDS:
                value = 1000;
                break;
            case MINUTES:
                value = 60000;
                break;
        }
        return value;
    }
}
