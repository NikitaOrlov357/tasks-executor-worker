package com.nesterov.tasksexecutor.worker.scheduler.dao;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
import lombok.extern.slf4j.Slf4j;

@Slf4j

public class ResultStore {
    private final Result result;
    private final Command command;
    private long workTime;


    public ResultStore(Result result, Command command) {
        this.result = result;
        this.command = command;
    }

    //Возвращаем время выполнения команды
    public long getWorkTime (Timer timer){
        return workTime = timer.getTime();
    }

    public void showLogOfSuccess(){
        log.info("command = {}, success = {} ", command, result.isSuccess());
    }

    public String getMessageOfResult(Result result){
        return result.getMessage();
    }


}
