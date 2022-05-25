package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.runners.RunnerResult;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.executor.service.execution.ExecutorFutureTask;
import com.nesterov.tasksexecutor.worker.executor.service.execution.ThreadLimiter;
import com.nesterov.tasksexecutor.worker.executor.dto.ExecutionResult;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class ExecutorThread extends Thread {
    private final Command command;
    private final Runner runner;
    private final ExternalConfigs.ExecutorConfig executorConfig;


    public ExecutorThread(Command command, Runner runner, ExternalConfigs.ExecutorConfig executorConfig) {
        this.command = command;
        this.runner = runner;
        this.executorConfig = executorConfig;
    }

    @Override
    public void run() {

        ExecutorFutureTask executorFutureTask = new ExecutorFutureTask(runner, command);
        Thread thread = new Thread(executorFutureTask);
        Timer timer = new Timer();
        ThreadLimiter threadLimiter = new ThreadLimiter(thread, executorConfig);
        RunnerResult runnerResult;
        thread.start();
        threadLimiter.start();
        timer.start();

        try {
            runnerResult = executorFutureTask.get();
        } catch (InterruptedException | ExecutionException e) {
           runnerResult = new RunnerResult(false,"the execution time was exceeded");
        } finally {
            timer.stop();
        }

        ExecutionResult executionResult = new ExecutionResult(runnerResult, command, timer.getTime());

        if (runnerResult != null) {
            log.info("Execution time :" + executionResult.getTime());
            log.info("command = {}, success = {} ", executionResult.getCommand(), executionResult.getRunnerResult().isSuccess());
            log.info("Message = {}", executionResult.getRunnerResult().getMessage());
            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }
    }
}
