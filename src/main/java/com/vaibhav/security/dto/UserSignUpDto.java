package com.vaibhav.security.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpDto {
    private String firstname;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
}
