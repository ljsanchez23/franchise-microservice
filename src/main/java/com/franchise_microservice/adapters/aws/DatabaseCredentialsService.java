package com.franchise_microservice.adapters.aws;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.franchise_microservice.adapters.aws.util.DatabaseCredentials;
import com.franchise_microservice.adapters.aws.util.exception.DatabaseCredentialsException;
import com.franchise_microservice.adapters.util.AdaptersConstants;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCredentialsService {

    private final SecretsManagerService secretsManagerService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DatabaseCredentialsService(SecretsManagerService secretsManagerService) {
        this.secretsManagerService = secretsManagerService;
    }

    public DatabaseCredentials getDatabaseCredentials() {
        try {
            return new DatabaseCredentials(
                    "adminaws",
                    "adminaws",
                    "myfirstdb.cf44wmsoe2qr.us-east-1.rds.amazonaws.com",
                    "3306",
                    "myfirstdb"
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseCredentialsException(AdaptersConstants.PROBLEM_CONNECTING_WITH_RDS_DATABASE);
        }
    }
}
