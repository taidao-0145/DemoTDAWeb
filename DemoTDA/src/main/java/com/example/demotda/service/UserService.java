package com.example.demotda.service;

import com.example.demotda.model.User;

import java.util.List;

public interface UserService {

    User findUserAndEmail(String username, String email);
    User findUserByUsername(String username);
    void save(User user);
    List<User> findAll();
    User findUserById(Long id);
    void deleteUser(Long id);
    List<User> Search(String name);

}
