package com.ankit.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
@PropertySource("classpath:application.properties")
public class DynamoDBClientConfig {

    @Value("${application.accessKey}")
    private String accessKey;

    @Value("${application.secretKey}")
    private String secretKey;

    @Bean
    public DynamoDbClient getDBClient() {
        //Instead of loading AWS Credentials from system config folder (via ProfileCredentialsProvider),
        //We are loading the credentials from
        //properties file (via AwsBasicCredentials)
        //ProfileCredentialsProvider pcp = ProfileCredentialsProvider.create();
        StaticCredentialsProvider scp = StaticCredentialsProvider.
                create(AwsBasicCredentials.create(accessKey, secretKey));
        Region region = Region.AP_SOUTH_1;
        DynamoDbClient ddb = DynamoDbClient.builder()
                .credentialsProvider(scp)
                .region(region)
                .build();
        return ddb;
    }

    @Bean
    public DynamoDbEnhancedClient getDynamoDbEnhancedClient(){
        return DynamoDbEnhancedClient.builder().dynamoDbClient(getDBClient()).build();
    }
}
