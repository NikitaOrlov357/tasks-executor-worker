package com.nesterov.tasksexecutor.worker.configs.applicationConfigs;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExternalConfigs {

    @Bean
    public ResultLoggerConfig getResultLoggerConfig(){
        return new ResultLoggerConfig();
    }

    @Getter
    public class ResultLoggerConfig{
        private String param;
    }
}
