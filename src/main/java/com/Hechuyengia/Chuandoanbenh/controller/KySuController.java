/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tranm
 */
@RestController
@RequestMapping("/taskbar-ks")
public class KySuController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public KySuController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/getuserdetail/{userId}")
//    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable("userId") Long userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserInfoDTO userInfo = userRepository.getUserInfoById(userId);
//
//        if (userInfo != null) {
//            return new ResponseEntity<>(userInfo, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
