package com.example.demotda.service;


import com.example.demotda.dto.UserDto;
import com.example.demotda.dto.UserProfileDto;
import com.example.demotda.model.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService {

    User findUserAndEmail(String username, String email);
    User findUserByUsername(String username);
    void save(UserProfileDto userProfileDto,String username,String email,int phone);
    List<User> findAll();
    User findUserById(Long id);
    void deleteUser(Long id);
    List<User> search(String name);

    String signUp(UserDto userDto, String rePass, Model model, HttpSession session);

    String addAccount(UserDto userDto,Model model);

}
