package com.example.demotda.config;

import com.example.demotda.model.AuthenticationProvider;
import com.example.demotda.model.User;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    @Autowired
    HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        System.err.println("Customer's email: "+email);

        User user= userService.findUserByEmail(email);
        if(user == null){
            userService.createNewUserOAuthPostLoginSuccess(email, AuthenticationProvider.GOOGLE);
        }
        else {
            session.setAttribute("user",user);
        }
        String name = oauthUser.getAttribute("name");
        System.err.println("Customer's name: "+name);
        //    userDetailsService.processOAuthPostLogin(email);
        super.onAuthenticationSuccess(request, response, authentication);
        response.sendRedirect("/user");
    }
}
