package com.nesterov.tasksexecutor.worker.executor.service.execution;

import com.nesterov.tasksexecutor.worker.executor.runners.RunnerResult;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.concurrent.FutureTask;

public class ExecutorFutureTask extends FutureTask<RunnerResult> {
    public ExecutorFutureTask(Runner runner, Command command){
        super(() -> runner.run(command));
    }
}
