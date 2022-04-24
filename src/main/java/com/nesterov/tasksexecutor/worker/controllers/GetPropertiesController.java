package com.nesterov.tasksexecutor.worker.controllers;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/properties")
public class GetPropertiesController {
    private final ExternalConfigs.SchedulerConfig schedulerConfig;

    @GetMapping(value = "/getRegularity")
    public long getRegularity(){
        return schedulerConfig.getSchedulerRegularity();
    }
}
