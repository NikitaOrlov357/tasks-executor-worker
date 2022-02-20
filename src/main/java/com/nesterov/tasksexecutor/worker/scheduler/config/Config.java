package com.nesterov.tasksexecutor.worker.scheduler.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private final String url;
    private final String user;
    private final String password;

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
        HikariDataSource hds = new HikariDataSource(hc);//говорит что hds лишний можно и удалить, но я не понял почему
        return hds;
    }

}
