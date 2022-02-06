package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.service.runners.CmdRunner;
import com.nesterov.tasksexecutor.worker.executor.service.runners.Runner;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    Runner runner;

    public ExecutorService(Runner runner){
        this.runner = runner;
    }

}
