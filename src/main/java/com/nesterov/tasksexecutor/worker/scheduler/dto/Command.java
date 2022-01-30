package com.nesterov.tasksexecutor.worker.scheduler.dto;

import lombok.Value;

import java.util.Date;

@Value
public class Command {
    int id;
    String command;
    String type;
    long regularity;
    long start;
    String owner;
    Date time;
}
