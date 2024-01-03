/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.BenhSuggestRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhSuggestRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungSuggestRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranm
 */
@Service
public class BenhSuggestService {

    @Autowired
    BenhSuggestRepository benhSuggestRepository;

    @Autowired
    BenhRepository benhRepository;

    @Autowired
    TrieuChungSuggestRepository trieuChungSuggestRepository;

    @Autowired
    TrieuChungBenhSuggestRepository trieuChungBenhSuggestRepository;

    @Autowired
    TrieuChungRepository trieuChungRepository;

    @Autowired
    TrieuChungBenhRepository trieuChungBenhRepository;

    @Transactional
    public List<String> saveListTrieuChungSuggest(UserEntity userEntity, String ten_benh, Long ma_benh, Long trang_thai, List<String> trieuChungList) {
        BenhEntity existingBenh = benhRepository.findById(ma_benh).orElse(null);
        System.out.println("BenhEntity: " + existingBenh);
        List<Boolean> existsList = new ArrayList<>(); // Tạo danh sách để lưu các kết quả exists
        List<String> trueTrieuChungList = new ArrayList<>(); // Khởi tạo danh sách để lưu tên triệu chứng khi exists là true
        
        //1. XỬ LÝ ĐỂ CHECK XEM TRIỆU CHỨNG THÊM MỚI ĐÓ ĐÃ CÓ Ở BỆNH ĐÓ CHƯA
        for (String tenTrieuChung : trieuChungList) {
            TrieuChungEntity existingTrieuChung = trieuChungRepository.findTrieuChungByTenTrieuChung(tenTrieuChung);
            System.out.println("TCEntity: " + existingTrieuChung);
            
            boolean exists = trieuChungBenhRepository.existsByBenhAndTrieuChung(existingBenh, existingTrieuChung);
            System.out.println("Exists in TrieuChungBenhEntity: " + exists);
            existsList.add(exists);
            if (exists) {
                trueTrieuChungList.add(tenTrieuChung); // Thêm tên triệu chứng vào danh sách khi exists là true
                
            }
        }
        //System.out.println("DS ten tc: " + trueTrieuChungList);
        int countTrue = 0;
        for (Boolean exist : existsList) {
            if (exist) {
                // tăng count lên khi có 1 phần tử là true
                countTrue++;
                 List<String> errorResult = new ArrayList<>();
            errorResult.addAll(trueTrieuChungList); // Thêm danh sách trueTrieuChungList vào kết quả lỗi
            errorResult.add("Error: Trung trieu chung");
            return errorResult;
            }
        }
        System.out.println("Count " + countTrue);
        //2. NẾU CHƯA CÓ THÌ THÊM MỚI
        if (countTrue == 0) {
            //lưu vào bảng bệnh suggest
            BenhSuggestEntity benhSuggestEntity = new BenhSuggestEntity();
            benhSuggestEntity.setMa_benh_trong_bang_benh(ma_benh);
            benhSuggestEntity.setTrang_thai(trang_thai);
            benhSuggestEntity.setUserEntity(userEntity);
            benhSuggestEntity.setTen_benh_suggest(ten_benh);
            BenhSuggestEntity saveBenhSuggest = benhSuggestRepository.save(benhSuggestEntity);

            // lưu vào bảng triệu chứng gợi ý
            for (String tenTrieuChung : trieuChungList) {
                TrieuChungSuggestEntity trieuChungSuggestEntity = new TrieuChungSuggestEntity();
                trieuChungSuggestEntity.setTen_trieu_chung_suggest(tenTrieuChung);
                TrieuChungSuggestEntity saveListTrieuChung = trieuChungSuggestRepository.save(trieuChungSuggestEntity);

                // Tạo mới đối tượng TrieuChungBenhsuggestEntity và lưu vào bảng liên kết
                TrieuChungBenhSuggestEntity trieuChungBenhSuggestEntity = new TrieuChungBenhSuggestEntity();
                trieuChungBenhSuggestEntity.setBenhSuggest(saveBenhSuggest);
                trieuChungBenhSuggestEntity.setTrieuChungSuggest(saveListTrieuChung);
                TrieuChungBenhSuggestEntity saveTrieuChungBenhSuggest = trieuChungBenhSuggestRepository.save(trieuChungBenhSuggestEntity);

            }
            
            
            
        } 
        return Collections.singletonList("Success");
    }

}
