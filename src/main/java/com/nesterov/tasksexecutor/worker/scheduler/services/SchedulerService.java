package com.nesterov.tasksexecutor.worker.scheduler.services;

import com.nesterov.tasksexecutor.worker.scheduler.dao.RegularTasksDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService extends Thread {

    RegularTasksDao regularTasksDao ;

    public SchedulerService (RegularTasksDao regularTasksDao){
        this.regularTasksDao = regularTasksDao;

        this.setName("schedulerServ");
    }

    @Override
    public void run() {
        while(true){ //"ошибка" т.к. не может завершиться без создания исключения
            System.out.println("hello world");
            try {
                System.out.println(getAllCommands());
                Thread.sleep(60000);//хз
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public List getAllCommands (){// мб их-за не проброшенной ошибки
        return regularTasksDao.getCurrentTasks();
    }

}
