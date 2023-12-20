/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.LienKetTrieuChungLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface LienKetTrieuChungLuatRepository extends JpaRepository<LienKetTrieuChungLuatEntity, Long> {

    @Query("SELECT l.luat.ma_luat FROM LienKetTrieuChungLuatEntity l "
            + "WHERE l.trieuChung.ma_trieu_chung = :ma_trieu_chung AND l.luat.loai_luat = :loai_luat")
    Long findLuatByMaTc(@Param("ma_trieu_chung") Long ma_trieu_chung, @Param("loai_luat") Long loai_luat);

    @Query("SELECT l FROM LienKetTrieuChungLuatEntity l "
            + "WHERE l.luat.ma_luat = :ma_luat AND l.trieuChung.ma_trieu_chung = :ma_trieu_chung")
    LienKetTrieuChungLuatEntity findByMaLuatAndMaTrieuChung(@Param("ma_luat") Long ma_luat, @Param("ma_trieu_chung") Long ma_trieu_chung);

}
