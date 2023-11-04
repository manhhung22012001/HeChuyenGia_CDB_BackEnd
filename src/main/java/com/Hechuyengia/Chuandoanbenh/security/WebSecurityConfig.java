/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean// Spring Boot tạo 1 bean từ kq của phương thức trước đó và đưa vào Spring context
    @Override
    public UserDetailsService userDetailsService() {// mã hóa tk
        return new UserDetailServiceImp();
    }

    ;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {// mã hóa mk
        return new BCryptPasswordEncoder();
    }

    ;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        // phương thức này cấu hình cách xác thực người dùng
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Phương thức này cấu hình các quy tắc bảo mật cho các request HTTP. 
        http
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/*").permitAll()//allow login api for all
                .antMatchers("/diagnosis/*").permitAll()
                
                .antMatchers("/taskbar-qtv/*").permitAll()
                .antMatchers("/taskbar-qtv/delete/*").permitAll()
                .antMatchers("/taskbar-qtv/edit/*").permitAll()
                .antMatchers("/taskbar-cg/*").permitAll()
                .antMatchers("/taskbar-cg/trieuchung/*").permitAll()
                .anyRequest().authenticated();
    }
}
