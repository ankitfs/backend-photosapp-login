package com.ankit.repository;

import com.ankit.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Repository
@PropertySource("classpath:application.properties")
public class RegistrationRepository {

    @Value("${application.table}")
    private String tableName;

    @Autowired
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public void saveUser(final UserEntity user) throws DynamoDbException{
        DynamoDbTable<UserEntity> userEntityTable = getTable();
        userEntityTable.putItem(user);
    }

    private DynamoDbTable<UserEntity> getTable() throws DynamoDbException{
        DynamoDbTable<UserEntity> userTable = dynamoDbEnhancedClient.
                table(tableName, TableSchema.fromBean(UserEntity.class));
        return userTable;
    }

}
