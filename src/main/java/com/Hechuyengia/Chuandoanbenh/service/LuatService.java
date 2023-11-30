/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LienKetBenhLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LienKetTrieuChungLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetBenhLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetTrieuChungLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
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
public class LuatService {

    @Autowired
    LuatRepository luatRepository;
    @Autowired
    LienKetBenhLuatRepository lienKetBenhLuatRepository;
    @Autowired
    BenhRepository benhRepository;
    @Autowired
    LienKetTrieuChungLuatRepository lienKetTrieuChungLuatRepository;
    @Autowired
    TrieuChungRepository trieuChungRepository;
    @Autowired
    TrieuChungBenhRepository trieuChungBenhRepository;

    @Transactional
    public void saveLuatLoai1(Long userId, Long loai_luat, Long ma_benh, List<Long> maTrieuChungList) {
        //1. Lưu thông tin luật vào bảng `luat`
        LuatEntity luatEntity = new LuatEntity();
        //luatEntity.setTen_luat(ten_luat);
        luatEntity.setLoai_luat(loai_luat);
        luatEntity.setId_user(userId);
        LuatEntity savedLuat = luatRepository.save(luatEntity);
        Long ma_luat = savedLuat.getMa_luat(); // Lấy ma_luat sau khi đã lưu thành công

        // Sử dụng đối tượng BenhEntity để gán vào LienKetBenhLuatEntity
        LienKetBenhLuatEntity lienKetBenhLuatEntity = new LienKetBenhLuatEntity();
        lienKetBenhLuatEntity.setLuat(savedLuat); // Gán đối tượng LuatEntity vào LienKetBenhLuatEntity
        BenhEntity benhEnitity = benhRepository.findById(ma_benh).orElse(null);
        if (benhEnitity != null) {
            lienKetBenhLuatEntity.setMaBenh(benhEnitity);
            //System.out.println("ma benhL "+benhEnitity);
            LienKetBenhLuatEntity savedLienKetBenhLuat = lienKetBenhLuatRepository.save(lienKetBenhLuatEntity);
        }
        // 3. Lưu thông tin về mối quan hệ giữa luật và triệu chứng vào bảng `lien_ket_trieu_chung_luat`

        LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
        lienKetTrieuChungLuatEntity.setLuat(savedLuat);
        List<TrieuChungEntity> trieuChungEntities = trieuChungRepository.findByTenTrieuChungIn1(maTrieuChungList);
        for (TrieuChungEntity trieuChungEntity : trieuChungEntities) {
            lienKetTrieuChungLuatEntity.setLuat(savedLuat);
            lienKetTrieuChungLuatEntity.setTrieuChung(trieuChungEntity);
            //System.out.println("ma tc "+trieuChungEntity);
            lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);

        }

    }

    @Transactional
    public void saveLuatLoai2(Long userId, Long loai_luat, Long ma_benh, List<Long> maTrieuChungList) {
        //1. Lưu thông tin luật vào bảng `luat`
        LuatEntity luatEntity = new LuatEntity();
        //luatEntity.setTen_luat(ten_luat);
        luatEntity.setLoai_luat(loai_luat);
        luatEntity.setId_user(userId);
        LuatEntity savedLuat = luatRepository.save(luatEntity);
        Long ma_luat = savedLuat.getMa_luat(); // Lấy ma_luat sau khi đã lưu thành công

        // Sử dụng đối tượng BenhEntity để gán vào LienKetBenhLuatEntity
        LienKetBenhLuatEntity lienKetBenhLuatEntity = new LienKetBenhLuatEntity();
        lienKetBenhLuatEntity.setLuat(savedLuat); // Gán đối tượng LuatEntity vào LienKetBenhLuatEntity
        BenhEntity benhEnitity = benhRepository.findById(ma_benh).orElse(null);
        if (benhEnitity != null) {
            lienKetBenhLuatEntity.setMaBenh(benhEnitity);
            //System.out.println("ma benhL "+benhEnitity);
            LienKetBenhLuatEntity savedLienKetBenhLuat = lienKetBenhLuatRepository.save(lienKetBenhLuatEntity);
        }
        // 3. Lưu thông tin về mối quan hệ giữa luật và triệu chứng vào bảng `lien_ket_trieu_chung_luat`

        LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
        lienKetTrieuChungLuatEntity.setLuat(savedLuat);
        List<TrieuChungEntity> trieuChungEntities = trieuChungRepository.findByTenTrieuChungIn1(maTrieuChungList);
        for (TrieuChungEntity trieuChungEntity : trieuChungEntities) {
            lienKetTrieuChungLuatEntity.setLuat(savedLuat);
            lienKetTrieuChungLuatEntity.setTrieuChung(trieuChungEntity);
            //System.out.println("ma tc "+trieuChungEntity);
            lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);

        }

    }

}
