package com.nesterov.tasksexecutor.worker.scheduler.dao;

import com.nesterov.tasksexecutor.worker.scheduler.dao.interfaces.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class RegularTasksDao implements CommandsDao {

    private final HikariDataSource hikariDataSource;

    public RegularTasksDao (HikariDataSource hikariDataSource){
        this.hikariDataSource = hikariDataSource;
        System.out.println(getCurrentTasks());
    }

    public List<Command> getCurrentTasks(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);//положить dataSource в аргумент
        long unixTimeInSeconds = System.currentTimeMillis() / 1000L;
        log.debug("unixTimeInSeconds = {} ", unixTimeInSeconds);
        String sql = " SELECT * FROM commands WHERE (((" + unixTimeInSeconds + " - start) / 60 * 60) % " + " regularity) " + " = 0 ";//sql запрос
        log.debug("sql = {} ", sql);

        return jdbcTemplate.query(
                sql,
                (rs, rowNum)->//посмотреть resultSet
                    new Command(
                            rs.getInt("id"),//название столбца из бд//
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
