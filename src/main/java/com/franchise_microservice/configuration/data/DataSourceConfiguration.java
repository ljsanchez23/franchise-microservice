package com.franchise_microservice.configuration.data;

import com.franchise_microservice.adapters.aws.DatabaseCredentialsService;
import com.franchise_microservice.adapters.aws.util.DatabaseCredentials;
import com.franchise_microservice.configuration.util.ConfigurationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    private final DatabaseCredentialsService databaseCredentialsService;

    public DataSourceConfiguration(DatabaseCredentialsService databaseCredentialsService) {
        this.databaseCredentialsService = databaseCredentialsService;
    }

    @Bean
    public DataSource dataSource() {
        DatabaseCredentials credentials = databaseCredentialsService.getDatabaseCredentials();

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(ConfigurationConstants.DATABASE_APP_DRIVER);
        dataSource.setUrl(String.format(
                ConfigurationConstants.DATABASE_APP_FORMAT,
                credentials.getHost(),
                credentials.getPort(),
                credentials.getDbName()
        ));
        dataSource.setUsername(credentials.getUsername());
        dataSource.setPassword(credentials.getPassword());

        return dataSource;
    }
}
