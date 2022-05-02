package com.nesterov.tasksexecutor.worker.logger;

import com.nesterov.tasksexecutor.worker.configs.applicationConfigs.ExternalConfigs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ResultLogger {

    private final DataSource hikariDataSource;
    private final ExternalConfigs.ResultLoggerConfig resultLoggerConfig;

    public void log(String command, boolean result, String message, String owner, Date start, long duration) {
        if (resultLoggerConfig.isResultLoggerDummyModeEnable()) {
            log.info("dummyMode for result logger command={}, result={}, message={}, owner={}, start={}, duration={}", command, result, message, owner, start, duration);
        } else {
            logByLogger(command, result, message, owner, start, duration);
        }
    }

    private void logByLogger(String command, boolean result, String message, String owner, Date start, long duration) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        String sql = "INSERT INTO log (command, result, message, owner, start, duration) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, command, result, message, owner, start, duration);
    }

}
