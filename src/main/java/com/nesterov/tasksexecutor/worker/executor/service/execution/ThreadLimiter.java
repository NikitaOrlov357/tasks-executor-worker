package com.nesterov.tasksexecutor.worker.executor.service.execution;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class ThreadLimiter extends Thread {
    private final Thread thread;
    private final ExternalConfigs.ExecutorConfig executorConfig;

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
