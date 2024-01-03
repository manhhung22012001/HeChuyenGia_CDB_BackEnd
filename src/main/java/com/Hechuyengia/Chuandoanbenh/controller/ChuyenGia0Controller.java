/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;


import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author tranm
 */
@RestController
@RequestMapping("/taskbar-cg0")
public class ChuyenGia0Controller {

    

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/userinfo/{userId}")
    public Map<String, Object> uploadFilesAndUserInfo(
            @PathVariable Long userId,
            @RequestParam(value = "anhdaidien", required = false) MultipartFile anhdaidien,// required = false không bắt buộc có
            @RequestParam(value = "bangTotNghiepYKhoa", required = true) MultipartFile bangTotNghiepYKhoa,
            @RequestParam(value = "chungChiHanhNghe", required = false) MultipartFile chungChiHanhNghe,
            @RequestParam(value = "chungNhanChuyenKhoa", required = false) MultipartFile chungNhanChuyenKhoa,
            @RequestParam(value = "hoc_ham", required = false) String hoc_ham,
            @RequestParam(value = "hoc_vi", required = false) String hoc_vi,
            @RequestParam(value = "status", required = false) String status
    ) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String result = userService.saveUserDetailsAndFiles(userId, anhdaidien, bangTotNghiepYKhoa, chungChiHanhNghe, chungNhanChuyenKhoa, hoc_ham, hoc_vi,status);
            //System.out.println("hoc ham: "+hoc_ham+" hoc vi: "+hoc_vi+" status"+ status);
            responseBody.put("message", "Success"); // Thêm thông điệp thành công vào body

            return responseBody;
        } catch (Exception e) {
            responseBody.put("message", "Error"); // Thêm thông điệp lỗi vào body

            return responseBody;
        }
    }

}
