/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import com.Hechuyengia.Chuandoanbenh.service.BenhMoiService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
public class BenhController {

    @Autowired
    BenhRepository benhRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BenhMoiService benhMoiService;

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
    @GetMapping("/trieuchung/{ma_benh}")
    public ResponseEntity<List<Object[]>> getTrieuChungByMaBenh(@PathVariable int ma_benh) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object[]> trieuchung = benhRepository.findTrieuChungByMaBenh(ma_benh);
        if (trieuchung != null && !trieuchung.isEmpty()) {
            return ResponseEntity.ok(trieuchung);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @CrossOrigin
//@PostMapping("/add-benh-va-trieu-chung/{userId}")
//public ResponseEntity<HttpStatus> addBenhVaTrieuChung(
//        @PathVariable("userId") Long userId,
//        @RequestBody Map<String, Object> requestBody) {
//    try {
//        String tenBenh = (String) requestBody.get("ten_benh");
//        String loaiHe = (String) requestBody.get("loai_he");
//        
//        // Assuming "trieu_chung" is a list of strings
//        List<String> trieuChungList = (List<String>) requestBody.get("trieu_chung");
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(" id la: " + userId + " ten_benh: " + tenBenh + " loaiHe: " + loaiHe + " trieuChungList: " + trieuChungList);
//
//        Optional<UserEntity> existingUser = userRepository.findById(userId);
//
//        benhMoiService.saveBenhVaTrieuChung(existingUser.get(), loaiHe, tenBenh, trieuChungList);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    } catch (Exception e) {
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}
@CrossOrigin
@PostMapping("/add-benh-va-trieu-chung/{userId}")
public ResponseEntity<HttpStatus> addBenhVaTrieuChung(
        @PathVariable("userId") Long userId,
        @RequestBody Map<String, Object> requestBody) {
    try {
        String tenBenh = (String) requestBody.get("ten_benh");
        String loaiHe = (String) requestBody.get("loai_he");

        // Assuming "trieu_chung" is a list of strings
        List<String> trieuChungList = (List<String>) requestBody.get("trieu_chung");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(" id la: " + userId + " ten_benh: " + tenBenh + " loaiHe: " + loaiHe + " trieuChungList: " + trieuChungList);

        Optional<UserEntity> existingUser = userRepository.findById(userId);

        benhMoiService.saveBenhVaTrieuChung(existingUser.get(), loaiHe, tenBenh, trieuChungList);

        return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


}
