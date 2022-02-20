package com.nesterov.tasksexecutor.worker.scheduler.services;

import com.nesterov.tasksexecutor.worker.executor.service.ExecutorService;
import com.nesterov.tasksexecutor.worker.scheduler.dao.RegularTasksDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService extends Thread {

    RegularTasksDao regularTasksDao;
    ExecutorService executorService;

    public SchedulerService (RegularTasksDao regularTasksDao, ExecutorService executorService){
        this.regularTasksDao = regularTasksDao;
        this.executorService = executorService;

        this.setName("schedulerServ");
    }

    @Override
    public void run() {
        while(true){ //"ошибка" т.к. не может завершиться без создания исключения
            try {
                getAllCommands().forEach(executorService::execute);
                Thread.sleep(60000);//хз
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Command> getAllCommands (){
        return regularTasksDao.getCurrentTasks();
    }

}
