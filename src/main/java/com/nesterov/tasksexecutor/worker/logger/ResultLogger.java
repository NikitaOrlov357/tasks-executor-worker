package com.nesterov.tasksexecutor.worker.logger;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.Date;

@Repository
public class ResultLogger {

    private final DataSource hikariDataSource;

    public ResultLogger(DataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
    }

    public void log (String command, boolean result, String message, String owner, Date start, long duration){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        String sql = "INSERT INTO log (command, result, message, owner, start, duration) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, command, result, message, owner, start, duration);

    }

}
