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

    @Getter
    public class ResultLoggerConfig{
        @Value("${result.logger.dummymode.enable}")
        boolean resultLoggerDummyModeEnable;

    }
}
