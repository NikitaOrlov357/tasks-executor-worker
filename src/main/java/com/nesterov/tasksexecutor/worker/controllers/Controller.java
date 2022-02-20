package com.nesterov.tasksexecutor.worker.controllers;

import com.nesterov.tasksexecutor.worker.scheduler.services.SchedulerService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    SchedulerService schedulerService;

    @GetMapping(value = "ok")
    public String showAddingPage () {
        return "ok";
    }

    @GetMapping(value = "hi")
    public String showAllCommands (Model model){
        List list = schedulerService.getAllCommands();//"ошибка" т.к. методы equals не определены четко в таком списке.
        model.addAttribute("commands", list);
        return "show";
    }
}
