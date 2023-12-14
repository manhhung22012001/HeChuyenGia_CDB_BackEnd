/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.LienKetBenhLuatEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface LienKetBenhLuatRepository extends JpaRepository<LienKetBenhLuatEntity, Long> {

    @Query("SELECT DISTINCT l.luat.ma_luat, l.luat.ten_luat, l.luat.loai_luat, tc.ma_trieu_chung, tc.ten_trieu_chung, b.ma_benh, b.ten_benh "
            + "FROM LienKetBenhLuatEntity l "
            + "JOIN l.luat luat "
            + "JOIN l.maBenh b "
            + "JOIN LienKetTrieuChungLuatEntity tcl ON tcl.luat = l.luat "
            + "JOIN tcl.trieuChung tc "
            + "WHERE b.ma_benh = :ma_benh")
    List<Object[]> getLuatAndTrieuChungByMaBenh(Long ma_benh);

    @Query("SELECT l.luat.ma_luat FROM LienKetBenhLuatEntity l "
            + "WHERE l.maBenh.ma_benh = :ma_benh AND l.luat.loai_luat = :loai_luat")
    Long findMaLuatByMaBenhAndLoaiLuat(Long ma_benh, Long loai_luat);

}
