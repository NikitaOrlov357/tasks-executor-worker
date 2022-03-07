package com.nesterov.tasksexecutor.worker.executor.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

public interface Runner {
     void run(Command command);

}
