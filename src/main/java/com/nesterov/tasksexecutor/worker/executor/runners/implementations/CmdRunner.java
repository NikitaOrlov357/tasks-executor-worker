package com.nesterov.tasksexecutor.worker.executor.runners.implementations;

import com.nesterov.tasksexecutor.worker.executor.runners.RunnerResult;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.logger.ResultLogger;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;

import static com.nesterov.tasksexecutor.worker.utils.StringUtils.isNotBlank;

@Slf4j
@Service
public class CmdRunner implements Runner {

    private final ResultLogger resultLogger;

    public CmdRunner (ResultLogger resultLogger){
        this.resultLogger = resultLogger;
    }

    @Override
    public RunnerResult run(Command command) {
        RunnerResult runnerResult;

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command.getCommand());
            runnerResult = getResult(process);
        }
        catch (IOException exception){
            runnerResult = new RunnerResult(false,"Process wasn't finish for Command " + command);
        }

        return runnerResult;
    }

    private String getErrorString(Process process) throws IOException {
       return StreamUtils.getStringFromStream(process.getErrorStream());
    }

    private String getInputString(Process process) throws IOException {
        return StreamUtils.getStringFromStream(process.getInputStream());
    }

    private RunnerResult getResult(Process process) throws IOException {
        String errorString = getErrorString(process);
        String inputString = getInputString(process);

        if (isNotBlank(errorString) && isNotBlank(inputString)){
            throw new RuntimeException();
        }

        if (isNotBlank(errorString)){
            return new RunnerResult(false, errorString);
        }
        else{
            return new RunnerResult(true, inputString);
        }
    }
}
