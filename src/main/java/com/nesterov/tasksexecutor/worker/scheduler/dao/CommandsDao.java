package com.nesterov.tasksexecutor.worker.scheduler.dao;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.List;

public interface CommandsDao {
    List<Command> getCurrentTasks();
}
