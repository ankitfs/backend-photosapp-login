package com.ankit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBClientConfig {

    @Bean
    public DynamoDbClient getDBClient() {
        ProfileCredentialsProvider pcp = ProfileCredentialsProvider.create();
        Region region = Region.AP_SOUTH_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .credentialsProvider(pcp)
                .region(region)
                .build();
        return ddb;
    }
}
