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
            BenhEntity benhEntity = new BenhEntity();
            // Tạo mới đối tượng BenhMoiEntity và lưu vào bảng bệnh mới

            benhEntity.setTen_benh(tenBenh);
            benhEntity.setLoai_he(loaiHe);
            benhEntity.setId_user(userEntity);

            BenhEntity savedBenh = benhRepository.save(benhEntity);

            Long ma_benh_moi = benhMoiRepository.finMaBenhMoiByTenBenhMoi(tenBenh);
            Optional<BenhMoiEntity> existingBenhMoi = benhMoiRepository.findById(ma_benh_moi);
            if (existingBenhMoi.isPresent()) {
                BenhMoiEntity benhToUpdate = existingBenhMoi.get();
                benhToUpdate.setGhi_chu(ghi_chu);
                benhMoiRepository.save(benhToUpdate);
            }

            // Tạo danh sách triệu chứng đã tồn tại trong cơ sở dữ liệu
            List<TrieuChungEntity> existingTrieuChungEntities = trieuChungRepository.findByTenTrieuChungIn(trieuChungList);

            // Lưu triệu chứng mới (nếu chưa tồn tại trong cơ sở dữ liệu)
            for (String tenTrieuChung : trieuChungList) {
                TrieuChungEntity existingTrieuChung = existingTrieuChungEntities.stream()
                        .filter(trieuChung -> tenTrieuChung.equals(trieuChung.getTen_trieu_chung()))
                        .findFirst()
                        .orElse(null);
                System.out.println("a+" + existingTrieuChung);
                if (existingTrieuChung == null) {
                    // Nếu triệu chứng không tồn tại, thêm mới vào cơ sở dữ liệu
                    TrieuChungEntity trieuChungEntity = new TrieuChungEntity();
                    trieuChungEntity.setTen_trieu_chung(tenTrieuChung);
                    TrieuChungEntity savedTrieuChung = trieuChungRepository.save(trieuChungEntity);

                    // Liên kết triệu chứng mới với bệnh
                    TrieuChungBenhEntity trieuChungBenhEntity = new TrieuChungBenhEntity();
                    trieuChungBenhEntity.setBenh(savedBenh);
                    trieuChungBenhEntity.setTrieuChung(savedTrieuChung);
                    trieuChungBenhRepository.save(trieuChungBenhEntity);
                } else {
//                    // Nếu triệu chứng đã tồn tại, chỉ thực hiện việc liên kết với bệnh
//                    TrieuChungBenhEntity trieuChungBenhEntity = new TrieuChungBenhEntity();
//                    trieuChungBenhEntity.setBenh(savedBenh);
//                    Long ma_trieu_chung = existingTrieuChung.getMa_trieu_chung(); // Lấy mã triệu chứng từ đối tượng đã tìm thấy
//                    trieuChungBenhEntity.setTrieuChung(ma_trieu_chung);
//
//
//                    trieuChungBenhRepository.save(trieuChungBenhEntity); // Lưu thông tin vào bảng TrieuChungBenhEntity

                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
