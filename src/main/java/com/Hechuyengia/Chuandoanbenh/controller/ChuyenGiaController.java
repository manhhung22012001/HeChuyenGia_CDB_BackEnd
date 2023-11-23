/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.BenhMoiService;
import com.Hechuyengia.Chuandoanbenh.service.TrieuChungService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.hibernate.annotations.common.util.impl.LoggerFactory.logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author tranm
 */
@RestController
@RequestMapping("/taskbar-cg")
//@PreAuthorize("hasRole('1')")
public class ChuyenGiaController {

    @Autowired
    BenhRepository benhRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BenhMoiService benhMoiService;

    @Autowired
    private TrieuChungRepository trieuChungRepository;
    
    private final TrieuChungService trieuchungService;
    
    @Autowired
    public ChuyenGiaController(TrieuChungService trieuchungService) {
        this.trieuchungService = trieuchungService;
    }
    @CrossOrigin
    @GetMapping("/getall")
    public List<BenhEntity> list() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return benhRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/getall12/{loai_he}")
    public ResponseEntity<List<BenhEntity>> list(@PathVariable String loai_he) {
        System.out.println("Ma loai he nhan duoc la " + loai_he);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<BenhEntity> result = benhRepository.findByLoaiHe(loai_he);
        // ... (process result if needed)
        return ResponseEntity.ok(result);
    }

    

    @CrossOrigin
    @PostMapping("/add-benh-va-trieu-chung/{userId}")
    public Map<String, Object> addBenhVaTrieuChung(
            @PathVariable("userId") Long userId,
            @RequestBody Map<String, Object> requestBody) {
            Map<String, Object> responseBody = new HashMap<>();
        try {
            
            String tenBenh = (String) requestBody.get("ten_benh");
            String loaiHe = (String) requestBody.get("loai_he");
            String trang_thai =(String) requestBody.get("trang_thai");
            // Assuming "trieu_chung" is a list of objects with a "trieu_chung" field
            List<Map<String, String>> trieuChungList = (List<Map<String, String>>) requestBody.get("trieu_chung");
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(" id la: " + userId + " ten_benh: " + tenBenh + " loaiHe: " + loaiHe + " trieuChungList: " + trieuChungList+ "trang thai: "+trang_thai);

            Optional<UserEntity> existingUser = userRepository.findById(userId);

            // Trích xuất tên triệu chứng từ mỗi đối tượng Map
            List<String> tenTrieuChungList = trieuChungList.stream()
                    .map(trieuChung -> trieuChung.get("trieu_chung"))
                    .collect(Collectors.toList());

            benhMoiService.saveBenhVaTrieuChung(existingUser.get(), loaiHe, tenBenh, tenTrieuChungList, trang_thai);

             responseBody.put("message", "Success"); // Thêm thông điệp thành công vào body

        return responseBody;
        } catch (Exception e) {
             responseBody.put("message", "Error"); // Thêm thông điệp lỗi vào body

        return responseBody;
        }
    }

    @CrossOrigin
    @GetMapping("/suggest")
    public ResponseEntity<List<String>> suggestTrieuChung(@RequestParam("keyword") String keyword) {
        //System.out.println("Keywork nhan duoc la: "+keyword);
//        keyword = "%" + keyword + "%";
        List<String> suggestedTrieuChung = trieuchungService.suggestTrieuChung(keyword);
        return ResponseEntity.ok(suggestedTrieuChung);
    }
    
    @GetMapping("/getuserdetail/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable("userId") Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserInfoDTO userInfo = userRepository.getUserInfoById(userId);

        if (userInfo != null) {
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}