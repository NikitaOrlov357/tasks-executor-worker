package com.nesterov.tasksexecutor.worker.executor.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

public interface Runner {
     RunnerResult run(Command command);
}
