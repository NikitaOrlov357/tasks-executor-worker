package com.nesterov.tasksexecutor.worker.scheduler.services;

import com.nesterov.tasksexecutor.worker.executor.service.ExecutorService;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dao.implementations.RegularTasksDbDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService extends Thread {

    private final CommandsDao commandsDao;
    private final ExecutorService executorService;


    public SchedulerService (@SuppressWarnings("all") CommandsDao commandsDao, ExecutorService executorService){//подавляем предупреждение т.к. Spring видит два бина с одинаковой сигнатурой, но в рантайме будет только один dao
        this.commandsDao = commandsDao;
        this.executorService = executorService;
        this.setName("schedulerServ");
    }

    @Override
    public void run() {
        while(true){
            try {
                getAllCommands().forEach(executorService::execute);
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Command> getAllCommands (){
        return commandsDao.getCurrentTasks();
    }

}
