package com.franchise_microservice.adapters.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ContainerCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Service
public class SecretsManagerService {
    private final SecretsManagerClient secretsManagerClient;

    public SecretsManagerService() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ContainerCredentialsProvider.builder().build())
                .build();
    }

    public String getSecret(String secretName) {
        GetSecretValueRequest request = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse response = secretsManagerClient.getSecretValue(request);

        return response.secretString();
    }
}
