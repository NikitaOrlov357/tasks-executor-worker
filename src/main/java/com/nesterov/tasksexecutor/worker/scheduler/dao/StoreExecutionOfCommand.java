package com.nesterov.tasksexecutor.worker.scheduler.dao;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Value
public class StoreExecutionOfCommand {
    Result result;
    Command command;
    long time;

}
