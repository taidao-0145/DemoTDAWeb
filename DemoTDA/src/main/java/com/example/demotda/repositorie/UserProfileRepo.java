package com.example.demotda.repositorie;

import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile,Long> {

    UserProfile findUserProfileByUser(User user);


}
