package com.ankit.service;

import com.ankit.dto.RegisterUserBean;
import com.ankit.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    //Method to Persist the User Registration Request
    public String processUserRegistration(RegisterUserBean registerUserBean) {

        String responseId = null;
        //TODO:: Server Side Validation of RegisterUserBean

        //Calling repository method to persist request data into database
        try {
            responseId = registrationRepository.saveRegistration(registerUserBean);

        }
        catch (DynamoDbException ddex) {
            System.err.println(ddex.getMessage());
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return responseId;
    }
}
