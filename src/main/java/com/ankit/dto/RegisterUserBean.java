package com.ankit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserBean {

    String firstName;
    String lastName;
    String email;
    String password;
    String confirmPassword;
    String gender;
    String dob;
}
