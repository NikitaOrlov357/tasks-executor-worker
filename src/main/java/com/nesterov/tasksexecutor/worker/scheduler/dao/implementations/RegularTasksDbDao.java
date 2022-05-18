package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import com.nesterov.tasksexecutor.worker.executor.service.CommandType;
import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Locale;

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
        String sql = " SELECT commands.id, command, type, regularity, start, name, time FROM commands INNER JOIN commands_type on commands.type_id = commands_type.id INNER JOIN owners on owners.id = commands.id WHERE (((" + unixTimeInMilliseconds + " - start) / " + regularity + " * " + regularity + ") % " + " regularity) " + " = 0 ";
        log.debug("sql = {} ", sql);

        return null;
    }
}
