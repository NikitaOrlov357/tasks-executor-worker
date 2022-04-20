package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.scheduler.dao.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
@ConditionalOnProperty(value = "database.dummymode.enable", matchIfMissing = true, havingValue = "false")
public class RegularTasksDbDao implements CommandsDao {

    private final DataSource hikariDataSource;

    public RegularTasksDbDao(DataSource hikariDataSource){
        this.hikariDataSource = hikariDataSource;
    }

    @Override
    public List<Command> getCurrentTasks(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        long unixTimeInMilliseconds = System.currentTimeMillis();
        log.debug("unixTimeInMilliseconds = {} ", unixTimeInMilliseconds);
        String sql = " SELECT * FROM commands WHERE (((" + unixTimeInMilliseconds + " - start) / 60000 * 60000) % " + " regularity) " + " = 0 ";
        log.debug("sql = {} ", sql);

        return jdbcTemplate.query(
                sql,
                (rs, rowNum)->
                    new Command(
                            rs.getInt("id"),
                            rs.getString("command"),
                            rs.getString("type"),
                            rs.getLong("regularity"),
                            rs.getLong("start"),
                            rs.getString("owner"),
                            rs.getDate("time")
                    )
        );
    }
}
