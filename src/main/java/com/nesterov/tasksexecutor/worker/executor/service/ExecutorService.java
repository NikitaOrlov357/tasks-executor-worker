package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.timer.TimeUnit;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExecutorService {
    private final RunnerSwitchService runnerSwitchService;

    public ExecutorService(RunnerSwitchService runnerSwitchService){
        this.runnerSwitchService = runnerSwitchService;
    }

    public void execute(Command command){
        Runner runner = runnerSwitchService.getRunner(command);
        if (runner != null) {
            new ExecutorThread(command, runner).start();
        }
        else {
            log.error("runner was not found for command = {}", command);
        }
    }
}
