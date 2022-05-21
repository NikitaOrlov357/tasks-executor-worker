package com.nesterov.tasksexecutor.worker.executor.runners.implementations;

import com.nesterov.tasksexecutor.worker.executor.runners.RunnerResult;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.nesterov.tasksexecutor.worker.scheduler.dto.CommandType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CmdRunnerTest {

    @Autowired
    CmdRunner cmdRunner;

    @Test
    public void getResultOfCorrectCommandTest() {
        Command command = new Command(
                123,
                "ping yandex.ru",
                CommandType.CMD,
                12312,
                123123,
                "owner",
                new Date()
        );

        RunnerResult result = cmdRunner.run(command);

        assertTrue(result.isSuccess());
        assertNotNull(result.getMessage());
    }

    @Test
    public void getResultOfIncorrectCommandTest() {
        Command command = new Command(
                123,
                "pingsdsfd sdf yandex.ru",
                CommandType.CMD,
                12312,
                123123,
                "owner",
                new Date()
        );

        String os = System.getProperty("os.name");

        if (!os.startsWith("Windows")){
            return;
        }

        RunnerResult result = cmdRunner.run(command);

        assertFalse(result.isSuccess());
        assertNotNull(result.getMessage());
    }
}
