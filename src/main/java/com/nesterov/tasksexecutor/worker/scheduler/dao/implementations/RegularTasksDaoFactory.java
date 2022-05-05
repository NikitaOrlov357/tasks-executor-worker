package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;


import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RegularTasksDaoFactory {

    @Bean
    public CommandsDao getCommandsDao(DataSource dataSource, ExternalConfigs.SchedulerConfig schedulerConfig,
                                      @Value("${database.dummymode.enable}") boolean dataBaseDummyModeEnable) {
        if (dataBaseDummyModeEnable) {
            return new RegularTasksDummyDao();
        }
        return new RegularTasksDbDao(dataSource, schedulerConfig);
    }

}
