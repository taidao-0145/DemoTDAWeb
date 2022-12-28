package com.example.demotda.service.impl;

import com.example.demotda.config.MailConfig;
import com.example.demotda.dto.UserDto;
import com.example.demotda.dto.UserProfileDto;
import com.example.demotda.model.User;
import com.example.demotda.model.UserProfile;
import com.example.demotda.repositorie.UserRepo;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;
    private MailConfig mailConfig;
    @Autowired
    public UserServiceImpl(UserRepo userRepo,MailConfig mailConfig) {
        this.mailConfig=mailConfig;
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
    public void save(UserProfileDto userProfileDto,String username,String email,int phone){
        User user= userRepo.findUserByUsername(username);
        UserProfile userProfile= new UserProfile(userProfileDto.getId(),userProfileDto.getFullName(),
                userProfileDto.getAddress1(),userProfileDto.getAddress2(),
                userProfileDto.getStreet(),userProfileDto.getCity());
        userProfile.setUser(user);
        user.setUserProfile(userProfile);
        user.setEmail(email);
        user.setPhone(phone);
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
    public List<User> search(String name) {
        return userRepo.search(name);
    }

    @Override
    public String signUp(UserDto userDto, String rePass, Model model, HttpSession session) {
        int test= 0;
        String emailPattern = "^[a-zA-Z0-9][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        String passPattern="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        User checkUser= userRepo.findUserByUsername(userDto.getUsername());
        User user= new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPass(userDto.getPass());
        if(checkUser != null){
            model.addAttribute("messusername","Tên đăng nhập đã tồn tại");
            test=1;
        }
        if(!rePass.equals(user.getPass())){
            model.addAttribute("messrepass","Nhập lại mật khẩu chưa chính xác");
            test=1;
        }
        if(!Pattern.matches(emailPattern,user.getEmail())){
            model.addAttribute("messmail","Nhập sai định dạng Email");
            test=1;
        }
        if(!Pattern.matches(passPattern,user.getPass())){
            model.addAttribute("messpass","Mật khẩu chưa đủ mạnh(> 8 ký tự,hoa,thường,kí tự...");
            test=1;
        }
        if(test==1){
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            model.addAttribute("phone",user.getPhone());
            model.addAttribute("pass",user.getPass());
            return "login/register";
        }
        else {
            user.setRole("USER");
            user.setPass(new BCryptPasswordEncoder().encode(user.getPass()));
            session.setAttribute("user",user);
            int code= (int) (Math.random()*100000);
            String code1= Integer.toString(code);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("daotai23092001@gmail.com");
            msg.setTo(user.getEmail());
            msg.setSubject("Xác thực đăng kí cho:"+ user.getEmail());
            msg.setText("Mã xác thực là: "+ code1);
            mailConfig.get().send(msg);
            session.setAttribute("code",code1);
            return "login/checkmail";
        }
    }
}
