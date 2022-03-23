package com.nesterov.tasksexecutor.worker.controllers;

import com.nesterov.tasksexecutor.worker.scheduler.services.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scheduler")
@Slf4j
public class SchedulerController {
    private final SchedulerService schedulerService;

    SchedulerController (SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @PostMapping("/stop")
    public String stopScheduler(){
        schedulerService.stopScheduler();
        log.info("Scheduler was stopped");
        return "stop";
    }

    @PostMapping("/start")
    public String startScheduler(){
        schedulerService.startScheduler();
        return "started";
    }
}
