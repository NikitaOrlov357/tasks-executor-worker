package com.nesterov.tasksexecutor.worker.executor.service;

import lombok.SneakyThrows;

public class ThreadLimiter extends Thread {
    Thread thread;

    public ThreadLimiter (Thread thread){
        this.thread = thread;
    }

    @Override
    public void run (){
        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
