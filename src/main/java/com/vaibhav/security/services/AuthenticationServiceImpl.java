package com.vaibhav.security.services;

import com.vaibhav.security.exception.InvalidCredential;
import com.vaibhav.security.models.Session;
import com.vaibhav.security.models.User;
import com.vaibhav.security.repository.SessionRepository;
import com.vaibhav.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
    private UserRepository userRepository;
    private SessionRepository sessionRepository;
    @Autowired
    AuthenticationServiceImpl(UserRepository userRepository,SessionRepository sessionRepository)
    {
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
    }
    @Override
    public void signUp(User user) throws InvalidCredential {
        if(userRepository.existsUserByEmail(user.getEmail()))
        {
            throw new InvalidCredential("Cannot use same email id");
        }
        user.setCreatedOn(new Date(System.currentTimeMillis()));
        userRepository.save(user);
    }

    @Override
    public String login(String email, String password) throws InvalidCredential {
        User u=userRepository.findUserByEmailAndPassword(email,password);
        if(u!=null)
        {
            String token=generateToken();
            Session s=new Session();
            s.setCreatedOn(new Date(System.currentTimeMillis()));
            s.setToken(token);
            s.setUser(u);
            sessionRepository.save(s);
            return token;
        }
        else {
            throw new InvalidCredential("Invalid email or password provided");
        }
    }

    @Override
    public boolean validate(String token) {
        return sessionRepository.existsSessionByToken(token);
    }
    private String generateToken()
    {
        String charset="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder str=new StringBuilder();
        Random random=new Random();
        for(int i=0;i<10;i++)
        {
            str.append(charset.charAt(random.nextInt(charset.length())));
        }
        return str.toString();
    }
}
