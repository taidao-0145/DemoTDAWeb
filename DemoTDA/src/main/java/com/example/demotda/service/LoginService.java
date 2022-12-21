package com.example.demotda.service;

import com.example.demotda.model.User;
import com.example.demotda.repositorie.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LoginService implements UserDetailsService {
    private  UserRepo userRepo;
    @Autowired
    public LoginService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User u= userRepo.findUserByUsername(username);
        List<GrantedAuthority> grantList= new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority(u.getRole());
        grantList.add(authority);
        if(u==null){
            throw new UsernameNotFoundException("not found");
        }
        org.springframework.security.core.userdetails.User currentUser= new org.springframework.security.core.userdetails.User(username,u.getPass(),grantList);
        return currentUser;

    }
}
