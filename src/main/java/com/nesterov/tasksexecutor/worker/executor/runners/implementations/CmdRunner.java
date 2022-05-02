package com.nesterov.tasksexecutor.worker.executor.runners.implementations;

import com.nesterov.tasksexecutor.worker.executor.runners.Result;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.logger.ResultLogger;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.Callable;

import static com.nesterov.tasksexecutor.worker.utils.StringUtils.isNotBlank;

@Slf4j
@Service
public class CmdRunner implements Runner {

    private final ResultLogger resultLogger;

    public CmdRunner (ResultLogger resultLogger){
        this.resultLogger = resultLogger;
    }

    @Override
    public Result run(Command command) {
        Result result;

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command.getCommand());
            result = getResult(process);
        }
        catch (IOException exception){
            result = new Result(false,"Process wasn't finish for Command " + command);
        }

        return result;
    }

    private String getErrorString(Process process) throws IOException {
       return StreamUtils.getStringFromStream(process.getErrorStream());
    }

    private String getInputString(Process process) throws IOException {
        return StreamUtils.getStringFromStream(process.getInputStream());
    }

    private Result getResult(Process process) throws IOException {
        String errorString = getErrorString(process);
        String inputString = getInputString(process);

        if (isNotBlank(errorString) && isNotBlank(inputString)){
            throw new RuntimeException();
        }

        if (isNotBlank(errorString)){
            return new Result(false, errorString);
        }
        else{
            return new Result(true, inputString);
        }
    }
}
