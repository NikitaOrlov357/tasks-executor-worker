package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.timer.TimeUnit;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
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
        Timer timer = new Timer();
        Result result = null;

        new Thread(executorFutureTask).start();
        timer.start();

        try {
            result = executorFutureTask.get();
            timer.stop();

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            long resultOfCommands = timer.getTime();
            log.info("Execution time :" + resultOfCommands);
            log.info("command = {}, success = {} ", command, result.isSuccess());
            log.info("Message = {}", result.getMessage());

            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }

    }
}
