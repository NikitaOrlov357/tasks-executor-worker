package com.nesterov.tasksexecutor.worker.scheduler.services;

import com.nesterov.tasksexecutor.worker.scheduler.dao.RegularTasksDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService extends Thread {

    RegularTasksDao regularTasksDao ;

    public SchedulerService (RegularTasksDao regularTasksDao){
        this.regularTasksDao = regularTasksDao;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("hello world");
            try {
                System.out.println(getAllCommands());
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public List getAllCommands (){
        return regularTasksDao.getCurrentTasks();
    }

}
