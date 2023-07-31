package com.example.demotda.config;

import com.example.demotda.config.oauth.CustomOAuth2UserService;
import com.example.demotda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Autowired
    private CustomOAuth2UserService auth2UserService;
    @Autowired
    private UserService userService;

    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.rememberMe().key("AbcdefgHiJKlmnOpqrsut0123456789").tokenValiditySeconds(1296000);

//        http.authorizeHttpRequests().antMatchers("/oauth2/**").permitAll().and().oauth2Login().loginPage("/login").userInfoEndpoint()
//                .userService(auth2UserService).and().successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                        Authentication authentication) throws IOException, ServletException {
//                        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
//                        String email = oauthUser.getAttribute("email");
//                        String name = oauthUser.getAttribute("name");
//                        System.err.println("Customer's email: " + email);
//                        userService.processOAuthPostLogin(oauthUser.getEmail());
//                        response.sendRedirect("/");
//                    }
//                });

        http.authorizeHttpRequests()
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/home","/","/register","/forgotpassword","/mailforgot","/changepassword","/checkmail").permitAll()


                .antMatchers("/admin").hasAnyRole("ADMIN","MANAGE","STAFF")
                .antMatchers("/admin/updateProduct","/admin/importProduct","/admin/exProduct").hasAnyRole("ADMIN","MANAGE")

                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/login/**","/user_static/**","/img/**","/js/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin().loginPage("/login").passwordParameter("pass")
                .permitAll().defaultSuccessUrl("/checkRole", true)
                .failureUrl("/login?e=Wrong login information")
                .and()
                .oauth2Login().loginPage("/login").userInfoEndpoint().userService(auth2UserService).and().successHandler(oAuth2LoginSuccessHandler)
                .and()
                .logout().logoutSuccessUrl("/login").permitAll();
        return http.build();





   }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

}
