package com.nesterov.tasksexecutor.worker.executor.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

public interface Runner {
     Result run(Command command);
}
