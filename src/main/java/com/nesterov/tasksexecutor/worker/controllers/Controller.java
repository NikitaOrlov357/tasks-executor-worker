package com.nesterov.tasksexecutor.worker.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping(value = "ok")
    public String showAddingPage () {
        return "ok";
    }


}
