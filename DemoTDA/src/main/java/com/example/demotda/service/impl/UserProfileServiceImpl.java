package com.example.demotda.service.impl;

import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.UserProfileRepo;
import com.example.demotda.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepo userProfileRepo;
    @Autowired
    public UserProfileServiceImpl(UserProfileRepo userProfileRepo) {
        this.userProfileRepo = userProfileRepo;
    }

    @Override
    public void addUserprofile(UserProfile userProfile){
        userProfileRepo.save(userProfile);
    }
    @Override
    public void updateProfile(UserProfile userProfile){
        userProfileRepo.save(userProfile);
    }
    @Override
    public UserProfile findUserProfileByUser(User user){
        return userProfileRepo.findUserProfileByUser(user);
    }

}
