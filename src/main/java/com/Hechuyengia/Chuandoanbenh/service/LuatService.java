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
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetBenhLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LienKetTrieuChungLuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.LuatRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungBenhRepository;
import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tranm
 */
@Service
public class LuatService {

    @Autowired
    LuatRepository luatRepository;
    private final LienKetBenhLuatRepository lienKetBenhLuatRepository;
    private final Gson gson; // Khai báo Gson

    @Autowired
    public LuatService(LienKetBenhLuatRepository lienKetBenhLuatRepository, Gson gson) {
        this.lienKetBenhLuatRepository = lienKetBenhLuatRepository;
        this.gson = gson;
    }
    @Autowired
    BenhRepository benhRepository;
    @Autowired
    LienKetTrieuChungLuatRepository lienKetTrieuChungLuatRepository;
    @Autowired
    TrieuChungRepository trieuChungRepository;
    @Autowired
    TrieuChungBenhRepository trieuChungBenhRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveLuatLoai1(Long userId, Long loai_luat, Long ma_benh, List<Long> maTrieuChungList) {
        //1. Lưu thông tin luật vào bảng `luat`
        LuatEntity luatEntity = new LuatEntity();
        //luatEntity.setTen_luat(ten_luat);
        luatEntity.setLoai_luat(loai_luat);
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        luatEntity.setUserEntity(userEntity);
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
        UserEntity userEntity = userRepository.findById(userId).orElse(null);
        luatEntity.setUserEntity(userEntity);
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

    public String getLuatAndTrieuChungByMaBenhAsJson(Long maBenh) {
        List<Object[]> data = lienKetBenhLuatRepository.getLuatAndTrieuChungByMaBenh(maBenh);
        return gson.toJson(data); // Chuyển đổi thành chuỗi JSON
    }

//    public Map<String, List<String>> separateTrieuChungMap(String trieuChungMap) {
//        Map<String, List<String>> separatedMap = new HashMap<>();
//
//        // Sử dụng thư viện Gson để phân tích chuỗi JSON thành JsonObject
//        JsonElement element = JsonParser.parseString(trieuChungMap);
//        JsonObject jsonObject = element.getAsJsonObject();
//
//        // Tạo danh sách cho "TC đã có trong csdl" và "Chưa có trong csdl"
//        List<String> existedInCSDL = new ArrayList<>();
//        List<String> notExistedInCSDL = new ArrayList<>();
//
//        // Duyệt qua các cặp key-value trong JsonObject để phân loại các giá trị
//        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue().getAsString();
//
//            if (key.startsWith("TC đã có trong csdl")) {
//                existedInCSDL.add(value.substring(value.indexOf(":") + 2));
//            } else if (key.startsWith("Chưa có trong csdl")) {
//                notExistedInCSDL.add(value.substring(value.indexOf(":") + 2));
//            }
//        }
//
//        // Lưu danh sách vào Map kết quả
//        separatedMap.put("TC đã có trong csdl", existedInCSDL);
//        separatedMap.put("Chưa có trong csdl", notExistedInCSDL);
//        System.out.println("Da cosL " + existedInCSDL);
//        System.out.println("Chua cosL " + notExistedInCSDL);
//        return separatedMap;
//    }
    public void saveLuatWithTrieuChungMoi(Long userId, Long ma_benh, List<Long> existedInDatabaseLong, List<Long> notExistInDatabaseLong) {
        Long luatLoai1 = lienKetBenhLuatRepository.findMaLuatByMaBenhAndLoaiLuat(ma_benh, 1L);
        Long luatLoai3 = lienKetBenhLuatRepository.findMaLuatByMaBenhAndLoaiLuat(ma_benh, 3L);

        System.out.println("ma luat loai 1  " + luatLoai1);
        System.out.println("ma luat loai 3  " + luatLoai3);

        if (luatLoai1 != null) {
            if (existedInDatabaseLong != null) {
                for (Long value : existedInDatabaseLong) {
                    // Xử lý từng giá trị trong mảng existedInDatabaseLong
                    TrieuChungEntity trieuChungEntity = trieuChungRepository.findById(value).orElse(null);
                    LuatEntity luatEntity = luatRepository.findById(luatLoai1).orElse(null);
                    LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
                    lienKetTrieuChungLuatEntity.setTrieuChung(trieuChungEntity);
                    lienKetTrieuChungLuatEntity.setLuat(luatEntity);
                    lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);
                }
            }
            // không có trường hợp else vì tất cả các bệnh đều có luật loại 1
        }

        if (luatLoai3 != null) {// nếu có luật loại 3
            if (notExistInDatabaseLong != null) {// nếu mảng triệu chứng khác null
                for (Long value : notExistInDatabaseLong) {
                    // Xử lý từng giá trị trong mảng notExistInDatabaseLong
                    TrieuChungEntity trieuChungEntity = trieuChungRepository.findById(value).orElse(null);
                    System.out.println("trieuChungEntity" + trieuChungEntity);
                    LuatEntity luatEntity = luatRepository.findById(luatLoai3).orElse(null);
                    System.out.println("luatEntity" + luatEntity);
                    LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
                    lienKetTrieuChungLuatEntity.setTrieuChung(trieuChungEntity);
                    lienKetTrieuChungLuatEntity.setLuat(luatEntity);
                    lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);

                }
            }
        } else {
            if (notExistInDatabaseLong != null) {
                for (Long value : notExistInDatabaseLong) {
                    //1. thêm luật mới
                    LuatEntity luatEntity = new LuatEntity();
                    //luatEntity.setTen_luat(ten_luat);
                    luatEntity.setLoai_luat(3L);
                    UserEntity userEntity = userRepository.findById(userId).orElse(null);
                    luatEntity.setUserEntity(userEntity);
                    LuatEntity savedLuat = luatRepository.save(luatEntity);
                    //2. tìm bệnh entity
                    BenhEntity benhEnitity = benhRepository.findById(ma_benh).orElse(null);

                    //3. thêm liên kết bệnh luật mới
                    LienKetBenhLuatEntity lienKetBenhLuatEntity = new LienKetBenhLuatEntity();
                    lienKetBenhLuatEntity.setLuat(savedLuat); // Gán đối tượng LuatEntity vào LienKetBenhLuatEntity
                    lienKetBenhLuatEntity.setMaBenh(benhEnitity);
                    LienKetBenhLuatEntity savedLienKetBenhLuat = lienKetBenhLuatRepository.save(lienKetBenhLuatEntity);
                    //4. tìm triệu chứng
                    TrieuChungEntity trieuChungEntity = trieuChungRepository.findById(value).orElse(null);
                    System.out.println("trieuChungEntity" + trieuChungEntity);
                    LienKetTrieuChungLuatEntity lienKetTrieuChungLuatEntity = new LienKetTrieuChungLuatEntity();
                    lienKetTrieuChungLuatEntity.setLuat(savedLuat);
                    lienKetTrieuChungLuatEntity.setTrieuChung(trieuChungEntity);
                    lienKetTrieuChungLuatRepository.save(lienKetTrieuChungLuatEntity);
                }
            }
        }
    }
}
