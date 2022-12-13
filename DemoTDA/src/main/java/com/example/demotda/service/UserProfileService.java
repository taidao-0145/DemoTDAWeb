package com.example.demotda.service;

import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.UserProfileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepo userProfileRepo;

    public void addUserprofile(UserProfile userProfile){

        userProfileRepo.save(userProfile);
    }
    public void UpdateProfile(UserProfile userProfile){

        userProfileRepo.save(userProfile);
    }

}
