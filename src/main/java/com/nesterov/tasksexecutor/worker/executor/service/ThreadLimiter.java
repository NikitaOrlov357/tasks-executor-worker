package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ThreadLimiter extends Thread {
    Thread thread;
    ExternalConfigs.ExecutorConfig executorConfig;

    @Override
    public void run (){
        try {
            sleep(executorConfig.getExecutorMaxExecutionTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }
}
