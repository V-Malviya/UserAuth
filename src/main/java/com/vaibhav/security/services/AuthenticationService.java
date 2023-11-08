package com.vaibhav.security.services;

import com.vaibhav.security.exception.InvalidCredential;
import com.vaibhav.security.models.User;

public interface AuthenticationService {
    void signUp(User user) throws InvalidCredential;
    String login(String email,String password) throws InvalidCredential;
    boolean validate(String token);
}
