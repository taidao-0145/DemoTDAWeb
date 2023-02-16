package com.example.demotda.config;

import com.example.demotda.service.impl.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Autowired
    private LoginService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/home","/","/register","/forgotpassword","/mailforgot","/changepassword").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user").hasRole("USER")
                .antMatchers("/login/**","/user/**","/img/**","/js/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .formLogin().loginPage("/login").passwordParameter("pass")
                .permitAll().defaultSuccessUrl("/checkRole", true)
                .failureUrl("/login?e=Wrong login information")
                .and().logout().permitAll().and().rememberMe().key("AbcdefgHiJKlmnOpqrsut0123456789").tokenValiditySeconds(3*24*60*60);
        return http.build();

//                .anyRequest().permitAll().and().csrf().disable()
//                .formLogin().loginPage("/login").passwordParameter("pass")
//                .permitAll().defaultSuccessUrl("/user", true)
//                .failureUrl("/login?e=Wrong login information")
//                .and().logout().permitAll().and().rememberMe().key("AbcdefgHiJKlmnOpqrsut0123456789").tokenValiditySeconds(3*24*60*60);
//        return http.build();
//


//        http.authorizeHttpRequests()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").passwordParameter("pass")
//                .permitAll().defaultSuccessUrl("/admin", true)
//                .failureUrl("/login?e=Wrong login information")
//                .and().logout().permitAll();
//        return http.build();
   }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

}
