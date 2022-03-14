package com.nesterov.tasksexecutor.worker.utils;

public class Timer {
    private long start;
    private long finish;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        finish = System.currentTimeMillis();
    }

    public long getTime() throws TimerRunException {
        if (finish <= 0) {
            throw new TimerRunException("Timer value is incorrect");
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
        switch (unit){
            case MILLISECONDS:
                return (finish - start);
            case SECONDS:
                return (finish - start) / 1000;
            case MINUTES:
                return (finish - start) / 60000;
        }

        return finish - start;
    }
}
