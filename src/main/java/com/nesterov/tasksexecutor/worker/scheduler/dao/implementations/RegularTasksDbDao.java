package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class RegularTasksDbDao implements CommandsDao {

    private final DataSource hikariDataSource;
    private final ExternalConfigs.SchedulerConfig schedulerConfig;

    @Override
    public List<Command> getCurrentTasks(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        long unixTimeInMilliseconds = System.currentTimeMillis();
        log.debug("unixTimeInMilliseconds = {} ", unixTimeInMilliseconds);
        long regularity = schedulerConfig.getSchedulerRegularity();
        String sql = " SELECT command.id, command.command, type, regularity, start, name, time FROM command INNER JOIN command_type on command_type.id = command.id INNER JOIN owner on owner.id = command.id WHERE ((( ?  - start) / ? * ?) %  ?) " + " = 0 ";
        Object[] values = new Object[] {unixTimeInMilliseconds, regularity, regularity, regularity};
        log.debug("sql = {} ", sql);

        return jdbcTemplate.query (sql, new CommandMapper(), values);
    }
}
