package com.example.AgileBoard.config;

import com.example.AgileBoard.repository.UserRepository;
import com.example.AgileBoard.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by User on 12/10/2017.
 */
@Component
//To enable Spring security's web support
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(new UserDetailsServiceImpl(userRepository))
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    //configure spring's httpSecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
