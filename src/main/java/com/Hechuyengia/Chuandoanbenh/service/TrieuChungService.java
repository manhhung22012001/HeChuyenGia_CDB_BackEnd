/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.BenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhMoiRepository;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

/**
 *
 * @author tranm
 */
@Service
public class TrieuChungService {

    private final TrieuChungRepository trieuchungRepository;
    
    @Autowired
    TrieuChungBenhRepository trieuChungBenhRepository;
    
    @Autowired
    BenhRepository benhRepository;
    
    @Autowired
    BenhMoiRepository benhMoiRepository;
    
    @Autowired
    TrieuChungRepository trieuChungRepository;
    
    @Autowired
    public TrieuChungService(TrieuChungRepository trieuchungRepository) {
        this.trieuchungRepository = trieuchungRepository;
    }

    public List<Object[]> getTrieuChungWithCountGreaterThanSix() {
        return trieuchungRepository.findTrieuChungWithCountGreaterThanSix();
    }

    public List<String> suggestTrieuChung(String keyword) {
        return trieuchungRepository.findByTen_trieu_chungContainingIgnoreCase(keyword);
    }

    public List<Object[]> getTrieuChungonly() {
        return trieuchungRepository.findTrieuChungonly();
    }
    
    @Transactional
    public void saveBenhVaTrieuChung(Long userEntity, String loaiHe, String tenBenh, List<String> trieuChungList, String ghi_chu) {
        try {
            
            // Tạo mới đối tượng BenhMoiEntity và lưu vào bảng bệnh mới
            BenhEntity benhEntity = new BenhEntity();
            benhEntity.setTen_benh(tenBenh);
            benhEntity.setLoai_he(loaiHe);
            benhEntity.setId_user(userEntity);
            
            BenhEntity savedBenh = benhRepository.save(benhEntity);
            
            // tìm ma_benh_moi dựa theo ten_benh_moi ở đây
            Long ma_benh_moi = benhMoiRepository.finMaBenhMoiByTenBenhMoi(tenBenh);      
            //System.out.println("Ma benh: "+ ma_benh_moi);
            Optional<BenhMoiEntity> existingBenhMoi = benhMoiRepository.findById(ma_benh_moi);
            if (existingBenhMoi.isPresent()) {
                BenhMoiEntity benhToUpdate = existingBenhMoi.get();
                benhToUpdate.setGhi_chu(ghi_chu);
                
                 benhMoiRepository.save(benhToUpdate);
            }
            // Lưu triệu chứng và liên kết với bệnh vừa tạo
            for (String tenTrieuChung : trieuChungList) {
                // Tạo mới đối tượng TrieuChungMoiEntity và lưu vào bảng triệu chứng mới
                TrieuChungEntity trieuChungEntity = new TrieuChungEntity();
                trieuChungEntity.setTen_trieu_chung(tenTrieuChung);
                TrieuChungEntity savedTrieuChung = trieuChungRepository.save(trieuChungEntity);

                // Tạo mới đối tượng TrieuChungBenhMoiEntity và lưu vào bảng liên kết
                TrieuChungBenhEntity trieuChungBenhEntity = new TrieuChungBenhEntity();
                trieuChungBenhEntity.setBenh(savedBenh);
                trieuChungBenhEntity.setTrieuChung(savedTrieuChung);
                trieuChungBenhRepository.save(trieuChungBenhEntity);
            }
        } catch (Exception e) {
            // Xử lý exception nếu có
            e.printStackTrace();
            throw e;
        }
    }
}
