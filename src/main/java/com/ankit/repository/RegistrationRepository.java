package com.ankit.repository;

import com.ankit.dto.RegisterUserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.HashMap;

@Repository
public class RegistrationRepository {

    private final String tableName = "photosapp_login_details";

    @Autowired
    private DynamoDbClient dbClient;

    public String saveRegistration(RegisterUserBean registerUserBean) throws DynamoDbException {
        HashMap<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put(DBTableEnum.firstname.name(), AttributeValue.builder().s(registerUserBean.getFirstName()).build());
        itemValues.put(DBTableEnum.lastname.name(), AttributeValue.builder().s(registerUserBean.getLastName()).build());
        itemValues.put(DBTableEnum.emailid.name(), AttributeValue.builder().s(registerUserBean.getEmail()).build());
        itemValues.put(DBTableEnum.password.name(), AttributeValue.builder().s(registerUserBean.getPassword()).build());
        itemValues.put(DBTableEnum.gender.name(), AttributeValue.builder().s(registerUserBean.getGender()).build());
        itemValues.put(DBTableEnum.dob.name(), AttributeValue.builder().s(registerUserBean.getDob()).build());
        PutItemRequest putItemRequest = PutItemRequest.builder().tableName(tableName).item(itemValues).build();
        PutItemResponse response = dbClient.putItem(putItemRequest);
        System.out.println(tableName+"\t was successfully updated. Response ID::\t"+response.responseMetadata().requestId());
        return response.responseMetadata().requestId();
    }

    enum DBTableEnum {

        firstname,
        lastname,
        emailid,
        password,
        gender,
        dob;

    }
}
