package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.List;

public class RegularTasksDummyDao implements CommandsDao {

    @Override
    public List<Command> getCurrentTasks() {
        return null;
    }
}
