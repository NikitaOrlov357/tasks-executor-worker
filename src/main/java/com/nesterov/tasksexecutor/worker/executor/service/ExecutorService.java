package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import lombok.AllArgsConstructor;
import com.nesterov.tasksexecutor.worker.utils.timer.TimeUnit;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ExecutorService {
    private final RunnerSwitchService runnerSwitchService;
    private final ExternalConfigs.ExecutorConfig executorConfig;

    public void execute(Command command){
        Runner runner = runnerSwitchService.getRunner(command);
        if (runner != null) {
            new ExecutorThread(command, runner,executorConfig).start();
           // long timeOfMethod = Timer.doAndGetTime(executorFutureTask::start, TimeUnit.MILLISECONDS);
        }
        else {
            log.error("runner was not found for command = {}", command);
        }
    }
}
