/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    TrieuChungRepository trieuChungRepository;

    @Autowired
    public KySuController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkTC/{userId}")
    public Map<String, Object> CheckTc(
            @PathVariable Long userId,
            @RequestParam(value = "ten_trieu_chung") List<String> ten_trieu_chung
    ) {
        Optional<UserEntity> existingUser = userRepository.findById(userId);
        Map<String, Object> responseBody = new HashMap<>();
        System.out.println("Ds "+ ten_trieu_chung);
        if (existingUser.isPresent()) {
            List<Long> existingTrieuChung = trieuChungRepository.findMaTrieuChungByTenTrieuChungIn(ten_trieu_chung);
            List<Long> maTrieuChungList = new ArrayList<>();
            System.out.println("Ds ma tim duoc "+ existingTrieuChung);
            for (Long tc : existingTrieuChung) {
                maTrieuChungList.add(tc); // Thêm mã triệu chứng vào danh sách
                System.out.println("Ds ma "+ maTrieuChungList);
            }
            responseBody.put("message", maTrieuChungList);
        } else {
            responseBody.put("message", "User không tồn tại"); // Người dùng không tồn tại
        }
        return responseBody;
    }

}
