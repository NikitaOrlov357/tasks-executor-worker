package com.nesterov.tasksexecutor.worker.utils;

import java.util.concurrent.TimeoutException;

public class Timer {
    private long start;
    private long finish;

    public void start() {
        start = System.currentTimeMillis();
    }

    public void stop() {
        finish = System.currentTimeMillis();
    }

    public long getTime() throws ArithmeticException{
            if (finish < start && finish == 0){
                throw new ArithmeticException();
            }
        return finish - start;
    }

    public interface MethodWithoutParams {
        void doMethod();
    }

    public static long doAndGetTime(MethodWithoutParams methodWithoutParams) {
        long start = System.currentTimeMillis();
        methodWithoutParams.doMethod();
        long finish = System.currentTimeMillis();
        return finish - start;
    }
}
