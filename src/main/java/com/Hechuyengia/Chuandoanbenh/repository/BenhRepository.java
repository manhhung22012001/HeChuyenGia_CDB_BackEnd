/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tranm
 */
public interface BenhRepository extends JpaRepository<BenhEntity, Long> {
//        @Query("SELECT tc.ten_trieu_chung, tc.ma_trieu_chung " +
//            "FROM TrieuChungEntity tc " +
//            "JOIN TrieuChungBenhEntity tcb ON tc.ma_trieu_chung = tcb.ma_trieu_chung " +
//            "JOIN BenhEntity b ON tcb.ma_benh = b.ma_benh " +
//            "WHERE b.ma_benh = :ma_benh")
//List<String> findTrieuChungByMaBenh(@Param("ma_benh") Long maBenh);

    @Query("SELECT tcb.trieuChung.ma_trieu_chung, tcb.trieuChung.ten_trieu_chung FROM TrieuChungBenhEntity tcb WHERE tcb.benh.ma_benh = :ma_benh")
    List<Object[]> findTrieuChungByMaBenh(@Param("ma_benh") int maBenh);

}
