package com.franchise_microservice.adapters.aws;

import com.fasterxml.jackson.databind.JsonNode;
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
            String secretName = AdaptersConstants.SECRET_NAME;




            String secretString = secretsManagerService.getSecret(secretName);
            JsonNode jsonNode = objectMapper.readTree(secretString);
            return new DatabaseCredentials(
                    jsonNode.get(AdaptersConstants.USERNAME_FROM_SECRET).asText(),
                    jsonNode.get(AdaptersConstants.PASSWORD_FROM_SECRET).asText(),
                    jsonNode.get(AdaptersConstants.HOST_FROM_SECRET).asText(),
                    jsonNode.get(AdaptersConstants.PORT_FROM_SECRET).asText(),
                    jsonNode.get(AdaptersConstants.DB_NAME_FROM_SECRET).asText()
            );
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
