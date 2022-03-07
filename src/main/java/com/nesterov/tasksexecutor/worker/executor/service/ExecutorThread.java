package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

public class ExecutorThread extends Thread{
    private final Runner runner;
    private final Command command;

    public ExecutorThread(Runner runner, Command command){
        this.runner = runner;
        this.command = command;
    }


    @Override
    public void run() {
        runner.run(command);
    }
}
