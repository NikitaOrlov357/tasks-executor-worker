package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.executor.service.CommandType;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegularTasksDummyDao implements CommandsDao {
    List<Command> list = new ArrayList<>();

    public RegularTasksDummyDao(){
        list.add(new Command(
                11,
                "dir",
                CommandType.CMD,
                60,
                1231233444,
                "odin",
                new Date()
        ));
        list.add(new Command(
                21,
                "ping vk.ru",
                CommandType.CMD,
                120,
                1231233444,
                "dava",
                new Date()
        ));
        list.add(new Command(
                23,
                "ping yandex.ru",
                CommandType.CMD,
                180,
                1231233444,
                "tri",
                new Date()
        ));
    }

    @Override
    public List<Command> getCurrentTasks() {
        return list;
    }
}
