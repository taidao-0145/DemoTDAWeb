package com.example.demotda.service;

import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

   public User findUserAndEmail(String username, String email){
       return userRepo.findByUsernameAndEmail(username,email);
   }
   public User findUserByUsername(String username){
       return userRepo.findUserByUsername(username);
   }
   public void save(User user){
        userRepo.save(user); 
   }


}
