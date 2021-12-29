package com.nesterov.tasksexecutor.worker.scheduler.services;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    public SchedulerService() throws InterruptedException {
        run();
    }

    public void run() throws InterruptedException {
        while(true){
            System.out.println("hello world");
            Thread.sleep(5000);
        }
    }
}
