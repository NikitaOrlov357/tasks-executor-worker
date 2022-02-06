package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.service.runners.CmdRunner;
import com.nesterov.tasksexecutor.worker.executor.service.runners.Runner;
import com.nesterov.tasksexecutor.worker.executor.utils.CommandTypes;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService {

    Runner cmdRunner;

    public ExecutorService(Runner cmdRunner){
        this.cmdRunner = cmdRunner;
    }

    public void execute(Command command){
        Runner runner = getRunner(command);
        runner.run(command);
    }

    @Nullable
    private Runner getRunner (Command command){
        switch (command.getType()){
            case CommandTypes.CMD:
                return cmdRunner;

            case CommandTypes.BASH:
                return null;
            default:
                return null;
        }
    }

}
