package com.nesterov.tasksexecutor.worker.scheduler.dao;

import com.nesterov.tasksexecutor.worker.scheduler.dao.interfaces.CommandsDao;
import com.nesterov.tasksexecutor.worker.scheduler.dto.Command;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RegularTasksDao implements CommandsDao {

    private final HikariDataSource hikariDataSource;

    public RegularTasksDao (HikariDataSource hikariDataSource){
        this.hikariDataSource = hikariDataSource;
    }

    public List getCurrentTasks(){

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);//положить dataSource в аргумент
        String sql = "SELECT * FROM scheduler";//sql запрос

        return jdbcTemplate.query(
                sql,
                (rs,rownum)->//посмотреть resultSet
                    new Command(
                            rs.getInt("id"),//название столбца из бд//
                            rs.getString("command"),
                            rs.getString("type"),
                            rs.getLong("regularity"),
                            rs.getLong("start"),
                            rs.getString("trigger"),
                            rs.getDate("time")
                    )
        );

    }



}
