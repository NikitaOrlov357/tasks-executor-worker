package com.nesterov.tasksexecutor.worker.logger;

import com.nesterov.tasksexecutor.worker.executor.service.ExecutorService;
import com.nesterov.tasksexecutor.worker.executor.service.ExecutorThread;
import com.nesterov.tasksexecutor.worker.utils.timer.TimeUnit;
import com.nesterov.tasksexecutor.worker.utils.timer.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;

@Repository
@Slf4j
public class ResultLogger {

    private final DataSource hikariDataSource;
    private final boolean resultLoggerDummyModeEnable;

    public ResultLogger(@Value("${result.logger.dummymode.enable}") boolean resultLoggerDummyModeEnable, DataSource hikariDataSource) {
        this.hikariDataSource = hikariDataSource;
        this.resultLoggerDummyModeEnable = resultLoggerDummyModeEnable;
    }

    public void log(String command, boolean result, String message, String owner, Date start, long duration) {
        if (resultLoggerDummyModeEnable) {
            log.info("dummyMode for result logger command={}, result={}, message={}, owner={}, start={}, duration={}", command, result, message, owner, start, duration);
        } else {
            logDefault(command, result, message, owner, start, duration);
        }
    }

    public void logDefault(String command, boolean result, String message, String owner, Date start, long duration) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        String sql = "INSERT INTO log (command, result, message, owner, start, duration) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, command, result, message, owner, start, duration);
    }

}
