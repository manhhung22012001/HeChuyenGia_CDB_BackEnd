/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tranm
 */
@RestController
@RequestMapping("/taskbar-qtv")

public class QuanTriVienController {

    @Autowired
    UserRepository userRepository;

    @CrossOrigin
    @GetMapping("/getall")
    public List<UserEntity> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findAll();
    }

    // API để xóa một người dùng theo ID
    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("Received DELETE request for user ID: " + userId);
        Optional<UserEntity> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") Long userId, @RequestBody UserEntity updatedUser) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("Received Update request for user ID: " + userId);
        Optional<UserEntity> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            UserEntity userToUpdate = existingUser.get();
            userToUpdate.setFullname(updatedUser.getFullname());
            userToUpdate.setPhonenumber(updatedUser.getPhonenumber());
            userToUpdate.setStatus(updatedUser.getStatus());
            userToUpdate.setEmail(updatedUser.getEmail());
            // Cập nhật các trường thông tin khác tương ứng

            UserEntity savedUser = userRepository.save(userToUpdate);
            return ResponseEntity.ok(savedUser);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
