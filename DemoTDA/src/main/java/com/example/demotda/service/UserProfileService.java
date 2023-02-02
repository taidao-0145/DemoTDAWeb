package com.example.demotda.service;

import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;



public interface UserProfileService {

    void addUserprofile(UserProfile userProfile);
    void updateProfile(UserProfile userProfile);
    UserProfile findUserProfileByUser(User user);

}
