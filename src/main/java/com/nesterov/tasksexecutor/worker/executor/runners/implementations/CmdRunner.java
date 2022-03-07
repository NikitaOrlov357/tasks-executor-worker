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

import static com.nesterov.tasksexecutor.worker.utils.StringUtils.isNotBlank;

@Slf4j
@Service
public class CmdRunner implements Runner {

    private final ResultLogger resultLogger;

    public CmdRunner (ResultLogger resultLogger){
        this.resultLogger = resultLogger;
    }

    @Override
    public void run(Command command) {
        Result result = null;
        Date date = null;

        try {
            date = new Date();
            Process process = Runtime.getRuntime().exec("cmd /c " + command.getCommand());
            result = getResult(process);

            log.debug("command = {}", command.getCommand());

            log.debug("result = {} message = {}", result.isSuccess(), result.getMessage());
        }
        catch (IOException exception){
            log.error("process was not finished for command = {}", command);
        }
        finally {
            resultLogger.log(command.getCommand(), result.isSuccess(), result.getMessage(), command.getOwner(), date, 121241124);
        }
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
