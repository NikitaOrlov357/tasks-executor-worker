package com.nesterov.tasksexecutor.worker.executor.runners;

import lombok.Value;

@Value
public class RunnerResult {
    boolean success;
    String message;
}
