package com.nesterov.tasksexecutor.worker.executor.runners;

import lombok.Value;

@Value
public class Result {
    boolean success;
    String message;
}
