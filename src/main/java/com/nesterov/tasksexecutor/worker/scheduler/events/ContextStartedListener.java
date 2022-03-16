package com.nesterov.tasksexecutor.worker.scheduler.events;

import com.nesterov.tasksexecutor.worker.scheduler.services.SchedulerService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextStartedListener implements ApplicationListener<ContextStartedEvent> {
    private final SchedulerService schedulerService;

    public ContextStartedListener(SchedulerService schedulerService){
        this.schedulerService = schedulerService;
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        schedulerService.start();
    }
}
