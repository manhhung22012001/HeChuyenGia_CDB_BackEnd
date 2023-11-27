/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LienKetBenhLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LienKetTrieuChungLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LuatEntity;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetBenhLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetTrieuChungLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LuatRepository;
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
    LienKetTrieuChungLuatRepository lienKetTrieuChungLuatRepository;

    @Transactional

    public void saveLuatLoai1(Long userId, Long loai_luat, String ten_luat, BenhEntity ma_benh, List<String> maTrieuChungList) {
        // 1. Lưu thông tin luật vào bảng `luat`
//        LuatEntity luatEntity = new LuatEntity();
//        luatEntity.setTen_luat(ten_luat);
//        luatEntity.setLoai_luat(loai_luat);
//        luatEntity.setId_user(userId);
//        LuatEntity savedLuat = luatRepository.save(luatEntity);
//        Long ma_luat = savedLuat.getMa_luat(); // Lấy ma_luat sau khi đã lưu thành công
//
//        /// Tìm đối tượng LuatEntity từ ma_luat
//        LuatEntity luat = luatRepository.findById(ma_luat).orElse(null);
//
//        if (luat != null) {
//            // Sử dụng đối tượng LuatEntity để gán vào LienKetBenhLuatEntity
//            LienKetBenhLuatEntity lienKetBenhLuatEntity = new LienKetBenhLuatEntity();
//            lienKetBenhLuatEntity.setLuat(luat); // Gán đối tượng LuatEntity vào LienKetBenhLuatEntity
//            lienKetBenhLuatEntity.setMa_benh(ma_benh.getMa_benh());
//            LienKetBenhLuatEntity savedLienKetBenhLuat = lienKetBenhLuatRepository.save(lienKetBenhLuatEntity);
//        }
//
//        // 3. Lưu thông tin về mối quan hệ giữa luật và triệu chứng vào bảng `lien_ket_trieu_chung_luat`
//        for (String ma_trieu_chung : maTrieuChungList) {
//            LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
//            lienKetTrieuChungLuatEntity.setLuat(ma_luat);
//            lienKetTrieuChungLuatEntity.setTrieuChung(ma_trieu_chung); // Giả sử có một phương thức để lấy `ma_trieu_chung` từ danh sách triệu chứng
//            lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);
//        }
    }

}
