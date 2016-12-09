package com.cgm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

@PropertySource({"classpath:persistence.properties"})
@SpringBootApplication
public class LicensingAuthServerApplication extends SpringBootServletInitializer {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(LicensingAuthServerApplication.class, args);
    }


    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean(initMethod = "migrate")
    public Flyway flywayDataSource() {
        final Flyway flyway = new Flyway();
        flyway.setDataSource(this.dataSource());
        flyway.setSqlMigrationSeparator("_");
        flyway.setBaselineOnMigrate(true);
        return flyway;
    }
}