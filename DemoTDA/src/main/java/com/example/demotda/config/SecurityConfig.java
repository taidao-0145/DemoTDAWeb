package com.example.demotda.config;

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


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().antMatchers("/store", "/viewCart","/checkout","/viewhome",
                        "/oder","/user").authenticated()
//                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().permitAll().and().csrf().disable()
                .formLogin().loginPage("/login").passwordParameter("pass")
                .permitAll().defaultSuccessUrl("/admin", true)
                .failureUrl("/login?e=Wrong login information")
                .and().logout().permitAll().and().rememberMe().key("AbcdefgHiJKlmnOpqrsut0123456789").tokenValiditySeconds(3*24*60*60);
        return http.build();
//        http.authorizeHttpRequests()
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .anyRequest().authenticated().and()
//                .formLogin().loginPage("/login").passwordParameter("pass")
//                .permitAll().defaultSuccessUrl("/user", true)
//                .failureUrl("/login?e=Wrong login information")
//                .and().logout().permitAll();
//        return http.build();
    }



}
