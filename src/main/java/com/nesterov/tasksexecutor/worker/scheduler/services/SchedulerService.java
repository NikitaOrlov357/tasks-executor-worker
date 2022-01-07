package com.nesterov.tasksexecutor.worker.scheduler.services;

import org.springframework.stereotype.Service;

@Service
public class SchedulerService extends Thread {

    @Override
    public void run() {
        while(true){
            System.out.println("hello world");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
