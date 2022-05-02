package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
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
        new Thread(executorFutureTask).start();
        Result result = null;
        try {
            result = executorFutureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        if (result != null) {
            log.info("command = {}, success = {} ", command, result.isSuccess());
            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }

    }
}
