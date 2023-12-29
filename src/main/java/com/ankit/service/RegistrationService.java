package com.ankit.service;

import com.ankit.dto.RegisterUserBean;
import com.ankit.entity.UserEntity;
import com.ankit.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;

import java.util.UUID;

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
            //responseId = registrationRepository.saveRegistration(registerUserBean);

            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(UUID.randomUUID().toString());
            userEntity.setFirstName(registerUserBean.getFirstName());
            userEntity.setLastName(registerUserBean.getLastName());
            userEntity.setPassword(registerUserBean.getPassword());
            userEntity.setEmail(registerUserBean.getEmail());
            userEntity.setGender(registerUserBean.getGender());
            userEntity.setDob(registerUserBean.getDob());
            registrationRepository.saveUser(userEntity);
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
