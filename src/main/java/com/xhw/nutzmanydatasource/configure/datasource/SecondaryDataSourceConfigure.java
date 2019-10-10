package com.xhw.nutzmanydatasource.configure.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class SecondaryDataSourceConfigure {

    @Value("${hikari.secondary.jdbcUrl}")
    private String jdbcUrl;
    @Value("${hikari.secondary.username}")
    private String username;
    @Value("${hikari.secondary.password}")
    private String password;
    @Value("${hikari.secondary.driverClassName}")
    private String driverClassName;

    @Bean(name = "secondaryDataSource")
    public HikariDataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setPoolName("primary");
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setMaximumPoolSize(5);
        hikariConfig.setMaxLifetime(1800000);
        hikariConfig.setMinimumIdle(1);
        hikariConfig.addDataSourceProperty("cachePrepStmts","true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize","250");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
        return new HikariDataSource(hikariConfig);
    }

    @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }
}
