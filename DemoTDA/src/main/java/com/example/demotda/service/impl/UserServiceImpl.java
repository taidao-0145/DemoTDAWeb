package com.example.demotda.service.impl;

import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User findUserAndEmail(String username, String email){
        return userRepo.findByUsernameAndEmail(username,email);
    }
    @Override
    public User findUserByUsername(String username){
        return userRepo.findUserByUsername(username);
    }
    @Override
    public void save(User user){
        userRepo.save(user);
    }
    @Override
    public List<User> findAll(){
        return userRepo.findAll();
    }
    @Override
    public User findUserById(Long id){
        return userRepo.findUserById(id);
    }
    @Override
    public void deleteUser(Long id){
        userRepo.deleteById(id);
    }

    @Override
    public List<User> Search(String name) {
        return userRepo.Search(name);
    }
}
