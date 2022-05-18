package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dao.StoreExecutionOfCommand;
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
        Result result;
        thread.start();
        threadLimiter.start();
        timer.start();

        try {
            result = executorFutureTask.get();
            timer.stop();

        } catch (InterruptedException | ExecutionException e) {

           result = new Result(false,"the execution time was exceeded");
        }
        StoreExecutionOfCommand storeExecutionOfCommand = new StoreExecutionOfCommand(result, command, timer.getTime());

        if (result != null) {
            log.info("Execution time :" + storeExecutionOfCommand.getTime());
            log.info("command = {}, success = {} ", storeExecutionOfCommand.getCommand(), storeExecutionOfCommand.getResult().isSuccess());
            log.info("Message = {}", storeExecutionOfCommand.getResult().getMessage());
            //resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }
    }
}
