package com.nesterov.tasksexecutor.worker.utils.timer;

public class TimerErrorException extends RuntimeException {
    public TimerErrorException(String message) {
        super(message);
    }
}
