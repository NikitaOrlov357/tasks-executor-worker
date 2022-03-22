package com.nesterov.tasksexecutor.worker.controllers;

import com.nesterov.tasksexecutor.worker.scheduler.services.SchedulerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scheduler")
public class SchedulerController {
    private final SchedulerService schedulerService;

    SchedulerController (SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @PostMapping("/stop")
    public String stopScheduler(){
        schedulerService.stopScheduler();
        return "stop";
    }

    @PostMapping("/start")
    public String startScheduler(){
        return "started";
    }
}
