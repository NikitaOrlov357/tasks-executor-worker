package com.nesterov.tasksexecutor.worker.scheduler.dao.implementations;

import com.nesterov.tasksexecutor.worker.executor.service.CommandType;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class CommandMapper implements RowMapper<Command> {

    @Override
    public Command mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Command(
                rs.getInt("id"),
                rs.getString("command"),
                CommandType.valueOf(rs.getString("type").toUpperCase(Locale.ROOT)),
                rs.getLong("regularity"),
                rs.getLong("start"),
                rs.getString("owner"),
                rs.getDate("time")
        );
    }
}
