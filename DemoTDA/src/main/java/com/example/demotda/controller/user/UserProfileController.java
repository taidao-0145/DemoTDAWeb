package com.example.demotda.controller.user;

import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.UserProfileRepo;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.UserProfileService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@Controller
@RequestMapping("/userProfile")
public class UserProfileController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserProfileService userProfileService;
    @Autowired
    private UserProfileRepo userProfileRepo;

    @GetMapping
    public String viewUser(Principal principal, Model model){
        String username= principal.getName();
        User user= userService.findUserByUsername(username);
        UserProfile userProfile= userProfileRepo.findUserProfileByUser(user);
        model.addAttribute("userprofile",userProfile);
        return "user/userprofile";
    }

    @PostMapping
    public String updateProfile(@RequestParam("username") String username,
                                @RequestParam("id") Long id,
                                @RequestParam("fullName") String fullName,
                                @RequestParam("address1") String address1,
                                @RequestParam("address2") String address2,
                                @RequestParam("street") String street,
                                @RequestParam("city") String city,
                                @RequestParam("email") String email,
                                @RequestParam("phone") int phone){
        User user = userService.findUserByUsername(username);
        user.setEmail(email);
        user.setPhone(phone);
        UserProfile userProfile= new UserProfile(id,fullName,address1,address2,street,city,user);
        user.setUserProfile(userProfile);
        userRepo.save(user);

        return "redirect:/userProfile";
    }
}
