package com.example.demotda.service;

import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo){
        this.userRepo=userRepo;
    }


}
