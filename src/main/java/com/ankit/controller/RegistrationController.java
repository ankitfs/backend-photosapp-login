package com.ankit.controller;

import com.ankit.dto.RegisterUserBean;
import com.ankit.dto.ResponseBean;
import com.ankit.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/api/register")
    public ResponseBean registerUserHandler(@RequestBody RegisterUserBean registerUserBean) {
        System.out.println("Handling Registration Request Entered");
        ResponseBean response = new ResponseBean(); //registrationService.processUserRegistration(registerUserBean);
        response.setStatusCode(200);
        if(response.getStatusCode() != 200) {
            response.setMessage("Error : User Registration Failed");
        }
        else {
            response.setMessage("Success: User Registration");
        }
        return response;
    }
}
