package com.nesterov.tasksexecutor.worker.scheduler.events;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

        @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    }
}
