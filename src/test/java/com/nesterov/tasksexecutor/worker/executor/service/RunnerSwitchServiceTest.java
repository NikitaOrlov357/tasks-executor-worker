package com.nesterov.tasksexecutor.worker.executor.service;

import com.nesterov.tasksexecutor.worker.scheduler.dto.CommandType;
import com.nesterov.tasksexecutor.worker.executor.runners.Runner;
import com.nesterov.tasksexecutor.worker.executor.runners.implementations.BashRunner;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class RunnerSwitchServiceTest {

    @MockBean
    RunnerSwitchService runnerSwitchService;

    @Test
    public void testServiceSwitchWithExistingService(){
        Command command =new Command(
                11,
                "dir",
                CommandType.CMD,
                60,
                1231233444,
                "odin",
                new Date()
        );

        when(runnerSwitchService.getRunner(any())).thenReturn(new BashRunner());

        Runner runner = runnerSwitchService.getRunner(command);

        System.out.println(runner.getClass());


    }
}
