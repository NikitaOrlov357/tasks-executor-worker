package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dao.ResultStore;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
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
        ThreadLimiter threadLimiter = new ThreadLimiter(thread, executorConfig);
        Result result = null;
        thread.start();
        threadLimiter.start();

        try {
            result = executorFutureTask.get();
        } catch (InterruptedException | ExecutionException e) {
           result = new Result(false,"the execution time was exceeded");
        }

        ResultStore resultStore = new ResultStore(result,command);


        if (result != null) {
            //resultStore.getSuccess(command, result.isSuccess());
            resultStore.showLogOfSuccess();
            log.info("Message = {}", resultStore.getMessageOfResult(result));
            //log.info("command = {}, success = {} ", command, result.isSuccess());

            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }
    }
}
