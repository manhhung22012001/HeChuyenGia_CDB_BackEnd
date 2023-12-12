/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungSuggestEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhSuggestRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhSuggestRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungSuggestRepository;
import java.util.List;
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
    BenhSuggestRepository BenhSuggestRepository;
    
    @Autowired
    TrieuChungSuggestRepository trieuChungSuggestRepository;
    
    @Autowired
    TrieuChungBenhSuggestRepository trieuChungBenhSuggestRepository;
    
    @Transactional
    public void saveListTrieuChungSuggest(UserEntity userEntity,String ten_benh,Long ma_benh,Long trang_thai,List<String> trieuChungList){
        try{
            //lưu vào bảng bệnh suggest
            BenhSuggestEntity benhSuggestEntity = new BenhSuggestEntity();
            benhSuggestEntity.setMa_benh_trong_bang_benh(ma_benh);
            benhSuggestEntity.setTrang_thai(trang_thai);
            benhSuggestEntity.setUserEntity(userEntity);
            benhSuggestEntity.setTen_benh_suggest(ten_benh);
            BenhSuggestEntity saveBenhSuggest = BenhSuggestRepository.save(benhSuggestEntity);
            
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
            
        }catch (Exception e) {
            // Xử lý exception nếu có
            e.printStackTrace();
            throw e;
        }
    }
}
