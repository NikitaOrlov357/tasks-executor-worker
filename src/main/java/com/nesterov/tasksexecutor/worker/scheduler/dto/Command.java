package com.nesterov.tasksexecutor.worker.scheduler.dto;

import com.nesterov.tasksexecutor.worker.executor.service.CommandTypes;
import lombok.Value;

import java.util.Date;

@Value
public class Command {
    int id;
    String command;
    CommandTypes type;
    long regularity;
    long start;
    String owner;
    Date time;

    @Override
    public String toString() {
        return "Command{" +
                "id=" + id +
                ", command='" + command + '\'' +
                ", type='" + type + '\'' +
                ", regularity=" + regularity +
                ", start=" + start + "(" + new Date(start) + ")" +
                ", owner='" + owner + '\'' +
                ", time=" + time +
                '}';
    }
}

