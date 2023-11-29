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
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author tranm
 */
@Service
public class TrieuChungService {

    private final TrieuChungRepository trieuchungRepository;
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    TrieuChungBenhRepository trieuChungBenhRepository;

    @Autowired
    BenhRepository benhRepository;

    @Autowired
    BenhMoiRepository benhMoiRepository;

    @Autowired
    TrieuChungRepository trieuChungRepository;

    @Autowired
    public TrieuChungService(DataSource dataSource, TrieuChungRepository trieuchungRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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
                    TrieuChungBenhEntity trieuChungBenhEntity = new TrieuChungBenhEntity();
                    trieuChungBenhEntity.setBenh(savedBenh);
                    trieuChungBenhEntity.setTrieuChung(existingTrieuChung);
                    System.out.println("exit: "+ existingTrieuChung);
                    trieuChungBenhRepository.save(trieuChungBenhEntity);
//                    Long ma_benh = savedBenh.getMa_benh();
//                    Long ma_trieu_chung = existingTrieuChung.getMa_trieu_chung();
//                    System.out.println("ma tc" + existingTrieuChung.getMa_trieu_chung() + "ma benh: " + ma_benh);
//                    String sql = "INSERT INTO trieu_chung_benh (ma_benh, ma_trieu_chung) VALUES (?, ?)";
//                    jdbcTemplate.update(sql, ma_benh, ma_trieu_chung);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
