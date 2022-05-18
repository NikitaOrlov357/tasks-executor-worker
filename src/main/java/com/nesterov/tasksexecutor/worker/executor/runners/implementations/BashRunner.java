package com.nesterov.tasksexecutor.worker.executor.runners.implementations;

import com.nesterov.tasksexecutor.worker.executor.runners.RunnerResult;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.stereotype.Service;

@Service
public class BashRunner implements Runner {

    @Override
    public RunnerResult run(Command command) {return null;
    }
}
