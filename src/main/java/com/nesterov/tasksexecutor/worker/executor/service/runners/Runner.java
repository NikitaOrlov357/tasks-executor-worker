package com.nesterov.tasksexecutor.worker.executor.service.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.Date;

public interface Runner {
     void run(Command command);

}
