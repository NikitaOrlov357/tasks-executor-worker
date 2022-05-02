package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.concurrent.FutureTask;

public class ExecutorFutureTask extends FutureTask<Result> {
    public ExecutorFutureTask(Runner runner, Command command){
        super(() -> runner.run(command));
    }
}
