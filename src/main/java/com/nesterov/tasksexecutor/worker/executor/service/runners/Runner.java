package com.nesterov.tasksexecutor.worker.executor.service.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

public interface Runner {
     void run (Command command);
}

