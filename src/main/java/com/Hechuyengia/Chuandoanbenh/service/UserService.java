/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.details.CustomUserDetails;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranm
 */
@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repo;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(UserEntity user) {
        repo.save(user);
    }

    public List<UserEntity> listAll() {
        return (List<UserEntity>) repo.findAll();
    }

    public UserEntity get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void saveUser(UserEntity user) {
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        repo.save(user);
    }
    


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(user); // CustomUserDetails là implement của UserDetails với các thông tin cần thiết của người dùng
    }
    public UserInfoDTO getUserInfo(Long userId) {
        return repo.getUserInfoById(userId);
    }
    
    


}
