package com.nesterov.tasksexecutor.worker.executor.service.runners;

import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.utils.StreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.nesterov.tasksexecutor.worker.utils.StringUtils.isNotBlank;

@Slf4j
@Service
public class CmdRunner implements Runner {

    @Override
    public void run(Command command) {
        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command.getCommand());
            String errorString = getErrorString(process);
            String inputString = getInputString(process);

            if (isNotBlank(errorString) && isNotBlank(inputString)){
                throw new RuntimeException();
            }

            log.debug("command = {}", command.getCommand());

            if (isNotBlank(errorString)){
                log.debug("failure, {}", errorString);
            }
            else {
                log.debug("success, {}", inputString);
            }
        }
        catch (IOException exception){
            // когда ядро не понимает команду
            log.error("process was not finished for command = {}", command);
        }
    }

    private String getErrorString(Process process) throws IOException {
       return StreamUtils.getStringFromStream(process.getErrorStream());
    }

    private String getInputString(Process process) throws IOException {
        return StreamUtils.getStringFromStream(process.getInputStream());
    }
}
