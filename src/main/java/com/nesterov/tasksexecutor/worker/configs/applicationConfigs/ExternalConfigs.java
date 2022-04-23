package com.nesterov.tasksexecutor.worker.configs.applicationConfigs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ExternalConfigs {

    @Bean
    public ResultLoggerConfig getResultLoggerConfig(){
        return new ResultLoggerConfig();
    }
    @Bean
    public ExecutorConfig getExecutorConfig(){
        return new ExecutorConfig();
    }

    @Bean
    public SchedulerConfig getSchedulerConfig(){
        return new SchedulerConfig();
    }

    @Getter
    public class ResultLoggerConfig{
        @Value("${result.logger.dummy.mode.enable}")
        boolean resultLoggerDummyModeEnable;

    }

    @Getter
    public class ExecutorConfig{
        @Value("${executor.max.execution.time}")
        long executorMaxExecutionTime;

    }

    @Getter
    public class SchedulerConfig{
        @Value("${scheduler.regularity}")
        long schedulerRegularity;
    }

}
