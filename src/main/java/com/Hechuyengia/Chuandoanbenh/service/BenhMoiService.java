/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungMoiEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhMoiRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhMoiRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungMoiRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranm
 */
@Service
public class BenhMoiService {

    @Autowired
    BenhMoiRepository benhMoiRepository;

    @Autowired
    TrieuChungMoiRepository trieuChungMoiRepository;

    @Autowired
    TrieuChungBenhMoiRepository trieuChungBenhMoiRepository;

    @Transactional
    public void saveBenhVaTrieuChung(UserEntity userEntity, String loaiHe, String tenBenh, List<String> trieuChungList) {
        try {
            // Tạo mới đối tượng BenhMoiEntity và lưu vào bảng bệnh mới
            BenhMoiEntity benhMoiEntity = new BenhMoiEntity();
            benhMoiEntity.setTen_benh_moi(tenBenh);
            benhMoiEntity.setLoai_he(loaiHe);
            benhMoiEntity.setUserEntity(userEntity);
            BenhMoiEntity savedBenh = benhMoiRepository.save(benhMoiEntity);

            // Lưu triệu chứng và liên kết với bệnh vừa tạo
            for (String tenTrieuChung : trieuChungList) {
                // Tạo mới đối tượng TrieuChungMoiEntity và lưu vào bảng triệu chứng mới
                TrieuChungMoiEntity trieuChungMoiEntity = new TrieuChungMoiEntity();
                trieuChungMoiEntity.setTen_trieu_chung_moi(tenTrieuChung);
                TrieuChungMoiEntity savedTrieuChung = trieuChungMoiRepository.save(trieuChungMoiEntity);

                // Tạo mới đối tượng TrieuChungBenhMoiEntity và lưu vào bảng liên kết
                TrieuChungBenhMoiEntity trieuChungBenhMoiEntity = new TrieuChungBenhMoiEntity();
                trieuChungBenhMoiEntity.setBenhMoi(savedBenh);
                trieuChungBenhMoiEntity.setTrieuChungMoi(savedTrieuChung);
                trieuChungBenhMoiRepository.save(trieuChungBenhMoiEntity);
            }
        } catch (Exception e) {
            // Xử lý exception nếu có
            e.printStackTrace();
            throw e;
        }
    }

}
