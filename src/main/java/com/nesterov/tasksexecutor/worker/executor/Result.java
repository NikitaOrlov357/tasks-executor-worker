package com.nesterov.tasksexecutor.worker.executor;

import lombok.Value;

@Value
public class Result {
    boolean success;
    String message;

}
