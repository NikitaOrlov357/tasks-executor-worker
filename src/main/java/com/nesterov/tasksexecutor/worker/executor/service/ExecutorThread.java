package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class ExecutorThread extends Thread {
    private final Command command;
    private final Runner runner;

    public ExecutorThread(Command command, Runner runner) {
        this.command = command;
        this.runner = runner;
    }

    @Override
    public void run() {
        ExecutorFutureTask executorFutureTask = new ExecutorFutureTask(runner, command);
        Thread thread = new Thread(executorFutureTask);
        ThreadLimiter threadLimiter = new ThreadLimiter(thread);
        Result result = null;
        thread.start();
        threadLimiter.start();

        try {
            result = executorFutureTask.get();
        } catch (InterruptedException | ExecutionException e) {
           result = new Result(false,"the execution time was exceeded");
        }
        if (result != null) {
            log.info("command = {}, success = {} ", command, result.isSuccess());
            log.info("Message = {}", result.getMessage());
            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }
    }
}
