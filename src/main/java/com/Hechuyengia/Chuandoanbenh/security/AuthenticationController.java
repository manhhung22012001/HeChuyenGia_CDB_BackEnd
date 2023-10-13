/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.security;

import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
/**
 *
 * @author tranm
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
     @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?> login( // Đây là phản hồi được trả về từ API.
            @RequestParam(value = "username", defaultValue = "") String username,// Sử dụng để chấp nhận các tham số từ yêu cầu HTTP. Trong trường hợp này, bạn chấp nhận username và password từ yêu cầu POST.
            @RequestParam(value = "password", defaultValue = "") String password) {
        UserEntity user = userRepository.findOne(username);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.valueOf(200));
        } else {
            return new ResponseEntity<>(null, HttpStatus.valueOf(404));
        }
    }
   @CrossOrigin
   @PostMapping("/register") // đăng ký
   public ResponseEntity<?> post(@RequestBody UserEntity input) {
       if(userRepository.existsByUsername(input.getUsername())){
            return new ResponseEntity<>(null, HttpStatus.valueOf(404));
       }
       else{
       userRepository.save(input);
       return new ResponseEntity<>(null, HttpStatus.valueOf(201));
       }
   }
     
}
