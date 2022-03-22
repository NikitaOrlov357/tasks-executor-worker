package com.nesterov.tasksexecutor.worker.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scheduler")
public class SchedulerController {

    @PostMapping("/stop")
    public String stopScheduler(){
        return "stop";
    }

    @PostMapping("/start")
    public String startScheduler(){
        return "started";
    }
}
