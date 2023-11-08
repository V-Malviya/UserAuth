package com.vaibhav.security.controllers;

import com.vaibhav.security.dto.UserSignUpDto;
import com.vaibhav.security.exception.InvalidCredential;
import com.vaibhav.security.models.User;
import com.vaibhav.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {
    private AuthenticationService authenticationService;
    @Autowired
    AuthenticationController(AuthenticationService authenticationService)
    {
        this.authenticationService=authenticationService;
    }
    @PostMapping("/signup")
    public String signUp(@RequestBody UserSignUpDto dto) throws InvalidCredential {
        authenticationService.signUp(userSignUpDtoToUser(dto));
        return "Sign-Up Success full";
    }
    @GetMapping("/login")
    public String login(@RequestParam String email,@RequestParam String password) throws InvalidCredential {
        String token =authenticationService.login(email, password);
        return token;
    }
    @GetMapping("/validate")
    public boolean validate(@RequestParam String token) throws InvalidCredential {
        if(token==null)
        {
            throw new InvalidCredential("No token provided by client");
        }
        return authenticationService.validate(token);
    }
    private User userSignUpDtoToUser(UserSignUpDto dto)
    {
        User user=new User();
        user.setFirstname(dto.getFirstname());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNo(dto.getPhoneNo());
        user.setPassword(dto.getPassword());
        return user;
    }
}
