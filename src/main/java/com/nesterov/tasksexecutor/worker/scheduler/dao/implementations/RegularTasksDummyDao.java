package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@ConditionalOnProperty(value = "database.dummymode.enable", matchIfMissing = true, havingValue = "true")
public class RegularTasksDummyDao implements CommandsDao {
    List<Command> list = new ArrayList<>();

    public RegularTasksDummyDao(){
        list.add(new Command(
                11,
                "dir",
                "cmd",
                60,
                1231233444,
                "odin",
                new Date()
        ));
        list.add(new Command(
                21,
                "ping vk.ru",
                "cmd",
                120,
                1231233444,
                "dava",
                new Date()
        ));
        list.add(new Command(
                23,
                "ping yandex.ru",
                "cmd",
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
