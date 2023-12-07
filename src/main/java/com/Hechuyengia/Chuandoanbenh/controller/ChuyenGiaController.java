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
import com.Hechuyengia.Chuandoanbenh.service.BenhService;
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
    BenhService benhService;
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

    @PostMapping("/add-benh-va-trieu-chung/{userId}")
    public Map<String, Object> addBenhVaTrieuChung(
            @PathVariable("userId") Long userId,
            @RequestBody Map<String, Object> requestBody) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            // Kiểm tra giá trị của 'ten_benh', 'loai_he' và 'trieu_chung'
            if (requestBody.containsKey("ten_benh") && requestBody.containsKey("loai_he") && requestBody.containsKey("trieu_chung")) {
                String tenBenh = (String) requestBody.get("ten_benh");
                String loaiHe = (String) requestBody.get("loai_he");
                String trang_thai = (String) requestBody.get("trang_thai");
                String ghi_chu = (String) requestBody.get("ghi_chu");

                List<Map<String, String>> trieuChungList = (List<Map<String, String>>) requestBody.get("trieu_chung");

                // Kiểm tra xem giá trị 'ten_benh', 'loai_he' và 'trieu_chung' có rỗng không
                if (tenBenh.isEmpty() || loaiHe.isEmpty() || trieuChungList.isEmpty()) {
                    responseBody.put("message", "Ten benh, loai he hoac trieu chung rong");
                    return responseBody;
                }

                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                System.out.println(" id la: " + userId + " ten_benh: " + tenBenh + " loaiHe: " + loaiHe + " trieuChungList: " + trieuChungList + "trang thai: " + trang_thai + " Ghi chu" + ghi_chu);

                Optional<UserEntity> existingUser = userRepository.findById(userId);

                List<String> tenTrieuChungList = trieuChungList.stream()
                        .map(trieuChung -> trieuChung.get("trieu_chung"))
                        .collect(Collectors.toList());

                benhMoiService.saveBenhVaTrieuChung(existingUser.get(), loaiHe, tenBenh, tenTrieuChungList, trang_thai, ghi_chu);

                responseBody.put("message", "Success");
                return responseBody;
            } else {
                responseBody.put("message", "Thieu tham so ten_benh, loai_he hoac trieu_chung");
                return responseBody;
            }
        } catch (Exception e) {
            responseBody.put("message", "Error");
            return responseBody;
        }
    }

    @GetMapping("/suggest")
    public ResponseEntity<List<String>> suggestTrieuChung(@RequestParam("keyword") String keyword) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //System.out.println("Keywork nhan duoc la: "+keyword);
//        keyword = "%" + keyword + "%";
        List<String> suggestedTrieuChung = trieuchungService.suggestTrieuChung(keyword);
        return ResponseEntity.ok(suggestedTrieuChung);
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> suggestTenBenhByKeyWord(@RequestParam("keyword") String keyword) {
        //System.out.println("Keywork nhan duoc la: " + keyword);

        List<String> suggestedTrieuChung = benhService.suggestTenBenh(keyword);
        //System.out.println("list: "+suggestedTrieuChung );
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
