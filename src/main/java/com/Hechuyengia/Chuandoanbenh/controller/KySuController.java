/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.BenhService;
import com.Hechuyengia.Chuandoanbenh.service.LuatService;
import com.Hechuyengia.Chuandoanbenh.service.TrieuChungService;
import com.Hechuyengia.Chuandoanbenh.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/taskbar-ks")
public class KySuController {

    @Autowired
    UserRepository userRepository;

    private final UserService userService;
    @Autowired
    TrieuChungRepository trieuChungRepository;
    @Autowired
    TrieuChungService trieuChungService;
    @Autowired
    BenhRepository benhRepository;
    @Autowired
    BenhService benhService;
    @Autowired
    LuatService luatService;

    @Autowired
    public KySuController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkTC/{userId}")
    public Map<String, Object> CheckTc(
            @PathVariable Long userId,
            @RequestParam(value = "ten_trieu_chung") String ten_trieu_chung
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> existingUser = userRepository.findById(userId);
        Map<String, Object> responseBody = new HashMap<>();
        System.out.println("Ds " + ten_trieu_chung);
        if (existingUser.isPresent()) {
            List<Long> maTrieuChungList = new ArrayList<>();
            String[] ten_trieu_chung_array = ten_trieu_chung.split("(?=\\p{Lu})"); // Ngắt xâu theo chữ cái in hoa ở đầu
            for (String ten : ten_trieu_chung_array) {
                Long maTrieuChung = trieuChungRepository.findMaTrieuChungByTenTrieuChung(ten);
                if (maTrieuChung != null) {
                    maTrieuChungList.add(maTrieuChung);
                } else {
                    maTrieuChungList.add(null); // Thêm mã null vào danh sách
                }
            }
            responseBody.put("message", maTrieuChungList);
        } else {

            responseBody.put("message", "Use không tồn tại"); // Người dùng không tồn tại
        }
        return responseBody;
    }

    @PostMapping("/add-Benh-and_TC/{userId}")
    public Map<String, Object> addBenhVaTrieuChung(
            @PathVariable("userId") Long userId,
            @RequestBody Map<String, Object> requestBody) {
        Map<String, Object> responseBody = new HashMap<>();
        try {

            String tenBenh = (String) requestBody.get("ten_benh");
            String loaiHe = (String) requestBody.get("loai_he");
            String ghi_chu = (String) requestBody.get("ghi_chu");
            //Long ma_benh_moi = (Long) requestBody.get("ma_benh_moi");
            // Assuming "trieu_chung" is a list of objects with a "trieu_chung" field
            List<Map<String, String>> trieuChungList = (List<Map<String, String>>) requestBody.get("trieu_chung");

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(" id la: " + userId + " ten_benh: " + tenBenh + " loaiHe: " + loaiHe + " trieuChungList: " + trieuChungList);
            Optional<UserEntity> existingUser = userRepository.findById(userId);

            // Trích xuất tên triệu chứng từ mỗi đối tượng Map
            List<String> tenTrieuChungList = trieuChungList.stream()
                    .map(trieuChung -> trieuChung.get("trieu_chung"))
                    .collect(Collectors.toList());

            trieuChungService.saveBenhVaTrieuChung(userId, loaiHe, tenBenh, tenTrieuChungList, ghi_chu);

            responseBody.put("message", "Success"); // Thêm thông điệp thành công vào body

            return responseBody;
        } catch (Exception e) {
            responseBody.put("message", "Error"); // Thêm thông điệp lỗi vào body

            return responseBody;
        }
    }

    @PostMapping("/lay-danh-sach-benh-da-co-luat")
    public Map<String, Object> getDSBenhDaCoLuat(@RequestBody Map<String, Object> requestBody) {
        Map<String, Object> responseBody = new HashMap<>();

        try {
            List<String> tenBenhList = (List<String>) requestBody.get("ten_benh");
            System.out.println("Danh sach nhan duoc: " + tenBenhList);

            // Gọi phương thức trong BenhService để lấy danh sách bệnh có luật
            List<Map<String, Object>> benhDaCoLuat = benhService.getDS(tenBenhList);
            System.out.println("Danh sách: " + benhDaCoLuat);
            // Gửi danh sách bệnh có luật về client

            responseBody.put("data", benhDaCoLuat);

        } catch (Exception e) {
            responseBody.put("success", false);
            responseBody.put("message", "Có lỗi xảy ra khi xử lý dữ liệu: " + e.getMessage());
        }

        return responseBody;
    }

    @PutMapping("save-luat-loai-1//{userId}")
    public Map<String, Object> saveLuatLoai1(
            @PathVariable("userId") Long userId,
            @RequestBody Map<String, Object> requestBody
    ) {
        Map<String, Object> responseBody = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Optional<UserEntity> existingUser = userRepository.findById(userId);
        
        if (existingUser.isPresent()) {
            Long loai_luat = (Long) requestBody.get("loai_luat");
            String ten_luat = (String) requestBody.get("ten_luat");
            BenhEntity ma_benh = (BenhEntity) requestBody.get("ma_benh");

            //Optional<UserEntity> existingUser = userRepository.findById(userId);
            List<Map<String, String>> trieuChungList = (List<Map<String, String>>) requestBody.get("ma_trieu_chung");
            // Trích xuất mã triệu chứng từ mỗi đối tượng Map
            List<String> maTrieuChungList = trieuChungList.stream()
                    .map(trieuChung -> trieuChung.get("ma_trieu_chung"))
                    .collect(Collectors.toList());
            //responseBody.put("message", maTrieuChungList);
            System.out.println("Loai luat: "+ loai_luat+ " Ten luat: "+ ten_luat+" Ma benh: "+ ma_benh+" ds TC: "+ maTrieuChungList);   
            
            luatService.saveLuatLoai1(userId,loai_luat,ten_luat,ma_benh,maTrieuChungList);
        } else {

            responseBody.put("message", "Use không tồn tại"); // Người dùng không tồn tại
        }
        return responseBody;
        
        

    }
}
