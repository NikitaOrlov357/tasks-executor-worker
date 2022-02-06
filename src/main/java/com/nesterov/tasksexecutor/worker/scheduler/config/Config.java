package com.nesterov.tasksexecutor.worker.scheduler.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private String url;
    private String user;
    private String password;

    public Config (@Value("${db.url}") String url,
                   @Value("${db.user}") String user,
                   @Value("${db.password}") String password){
        this.url = url;
        this.user = user;
        this.password = password;

    }

    @Bean
    public HikariDataSource getHikariDataSource (){
        HikariConfig hc = new HikariConfig();
        hc.setUsername(user);
        hc.setPassword(password);
        hc.setJdbcUrl(url);
        HikariDataSource hds = new HikariDataSource(hc);
        return hds;
    }

}
