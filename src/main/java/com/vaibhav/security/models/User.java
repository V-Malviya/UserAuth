package com.vaibhav.security.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class User extends BaseModel{
    private String firstname;
    private String lastName;
    private String email;
    private String phoneNo;
    private String password;
}
