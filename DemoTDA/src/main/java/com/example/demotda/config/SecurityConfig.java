package com.example.demotda.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.beans.BeanProperty;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/store","/viewcart","/checkout","/viewhome",
                        "/oder").authenticated()
//                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll().and().csrf().disable()
                .formLogin().loginPage("/login").passwordParameter("pass")
                .permitAll().defaultSuccessUrl("/user", true)
                .failureUrl("/login?e=Wrong login information")
                .and().logout().permitAll();
        return http.build();
    }



}
