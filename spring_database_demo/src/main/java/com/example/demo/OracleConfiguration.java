package com.example.demo;

import oracle.jdbc.pool.OracleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
 
@Configuration
@ConfigurationProperties(prefix="oracle")
//@PropertySource("classpath:application.properties")
public class OracleConfiguration {
    @NotNull
    private String username;
 
    @NotNull
    private String password;
 
    @NotNull
    private String url;
 
    public void setUsername(String username) {
        this.username = username;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public void setUrl(String url) {
        this.url = url;
    }
 
    @Bean
    DataSource dataSource() throws SQLException {
 
        OracleDataSource dataSource = new OracleDataSource();
        
        /*dataSource.setUser("system");
        dataSource.setPassword("Oracle123");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:orcl");*/
        
        
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        
        System.out.println("username::: "+username);
        System.out.println("password::: "+password);
        System.out.println("url::: "+url);
        
        dataSource.setImplicitCachingEnabled(true);
        dataSource.setFastConnectionFailoverEnabled(true);
        Connection conn = dataSource.getConnection();
  
        System.out.println("conn::::"+conn);
        return dataSource;
    }
}