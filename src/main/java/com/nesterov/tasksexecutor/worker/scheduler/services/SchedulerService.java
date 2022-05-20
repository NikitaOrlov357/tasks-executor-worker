package com.nesterov.tasksexecutor.worker.scheduler.services;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.service.execution.ExecutorService;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService extends Thread {

    private final CommandsDao commandsDao;
    private final ExecutorService executorService;
    private final ExternalConfigs.SchedulerConfig schedulerConfig;
    private boolean stopped = false;

    public SchedulerService(@SuppressWarnings("all") CommandsDao commandsDao, ExecutorService executorService, ExternalConfigs.SchedulerConfig schedulerConfig) {//подавляем предупреждение т.к. Spring видит два бина с одинаковой сигнатурой, но в рантайме будет только один dao
        this.commandsDao = commandsDao;
        this.executorService = executorService;
        this.schedulerConfig = schedulerConfig;

        this.setName("schedulerServ");
    }

    @Override
    public void run() {
        while (true) {
            if (!stopped) {
                getAllCommands().forEach(executorService::execute);
            }

            try {
                Thread.sleep(schedulerConfig.getSchedulerRegularity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopScheduler() {
        stopped = true;
    }

    public void startScheduler() {
        stopped = false;
    }


    public List<Command> getAllCommands() {
        return commandsDao.getCurrentTasks();
    }

}
