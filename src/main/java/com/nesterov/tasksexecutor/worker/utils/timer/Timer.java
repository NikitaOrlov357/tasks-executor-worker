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
        if (finish <= 0) {
            throw new TimerErrorException("Timer value is incorrect");
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
        long value = 0;
        switch (unit){
            case MILLISECONDS:
                value = 1;
                break;
            case SECONDS:
                value = 1000;
                break;
            case MINUTES:
                value = 60000;
                break;
        }

        return (finish - start) / value;
    }
}
