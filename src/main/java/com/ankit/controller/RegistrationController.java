package com.ankit.controller;

import com.ankit.dto.RegisterUserBean;
import com.ankit.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/api/register")
    public String registerUserHandler(@RequestBody RegisterUserBean registerUserBean) {
        System.out.println("Handling Registration Request Entered");
        String response = registrationService.processUserRegistration(registerUserBean);
        return response == null ? "Error:: User Registration Failed" : "Success: User Registration";
    }
}
