package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.executor.runners.implementations.BashRunner;
import com.nesterov.tasksexecutor.worker.executor.runners.implementations.CmdRunner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class RunnerSwitchService {
    private final CmdRunner cmdRunner;
    private final BashRunner bashRunner;

    RunnerSwitchService(CmdRunner cmdRunner, BashRunner bashRunner) {
        this.cmdRunner = cmdRunner;
        this.bashRunner = bashRunner;
    }

    @Nullable
    public Runner getRunner(Command command) {
        switch (command.getType()) {
            case CommandTypes.CMD:
                return cmdRunner;
            case CommandTypes.BASH:
                return bashRunner;
            default:
                return null;
        }
    }
}
