package com.example.demotda.service.impl;

import com.example.demotda.config.MailConfig;
import com.example.demotda.dto.UserDto;
import com.example.demotda.dto.UserProfileDto;
import com.example.demotda.model.AuthenticationProvider;
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
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    HttpSession session;
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
            user.setRole("ROLE_USER");
            user.setImg("img/avt.png");
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

    @Override
    public String addAccount(UserDto userDto,Model model) {
        User user= new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());
        user.setPass(userDto.getPass());
        user.setRole(userDto.getRole());
        user.setImg("img/avt.png");

        User checkUser= userRepo.findUserByUsername(userDto.getUsername());

        String emailPattern = "^[a-zA-Z0-9][\\w-]+@([\\w]+\\.[\\w]+|[\\w]+\\.[\\w]{2,}\\.[\\w]{2,})$";
        String passPattern="(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
        int test=0;

        if(checkUser != null){
            model.addAttribute("messusername","Tên đăng nhập đã tồn tại");
            test=1;
        }
        if(!Pattern.matches(passPattern,user.getPass())){
            model.addAttribute("messpass","Mật khẩu chưa đủ mạnh(> 8 ký tự,hoa,thường,kí tự...)");
            test=1;
        }
        if(!Pattern.matches(emailPattern,user.getEmail())){
            model.addAttribute("messmail","Nhập sai định dạng Email");
            test=1;
        }
        if(test==1){
            model.addAttribute("username",user.getUsername());
            model.addAttribute("email",user.getEmail());
            model.addAttribute("phone",user.getPhone());
            model.addAttribute("pass",user.getPass());
            return "admin/addAccount";
        }
        else {

            UserProfile userProfile= new UserProfile("update","update","update","update","update");
            user.setUserProfile(userProfile);
            userProfile.setUser(user);
            user.setPass(new BCryptPasswordEncoder().encode(user.getPass()));
            userRepo.save(user);
            return "redirect:/userManagement";
        }
    }

    @Override
    public void processOAuthPostLogin(String email){
        User user = userRepo.findByEmail(email);
        if(user == null){
            user.setImg("20190913_224545.jpg");
            user.setEmail(email);
            user.setUsername(email);
            user.setRole("ROLE_USER");
        }
        session.setAttribute("emailUser", email);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public void createNewUserOAuthPostLoginSuccess(String email, AuthenticationProvider provider) {
        User user= new User();
        user.setRole("ROLE_USER");
        user.setEmail(email);
        user.setUsername(email);
        user.setAuthProvider(provider);
        user.setImg("img/avt.png");

        UserProfile userProfile= new UserProfile("update","update","update","update","update");
        user.setUserProfile(userProfile);
        userProfile.setUser(user);

        userRepo.save(user);

        session.setAttribute("user",user);
    }
}
