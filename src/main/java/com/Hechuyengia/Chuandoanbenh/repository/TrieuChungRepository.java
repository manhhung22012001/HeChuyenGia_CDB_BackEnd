/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;


import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.entity.LienKetTrieuChungLuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 *
 * @author tranm
 */
public interface TrieuChungRepository extends JpaRepository<TrieuChungEntity, Long>{
         @Query("SELECT tc.ma_trieu_chung, tc.ten_trieu_chung, COUNT(*) " +
       "FROM TrieuChungBenhEntity tcb " +
       "JOIN tcb.trieuChung tc " +
       "GROUP BY tc.ma_trieu_chung, tc.ten_trieu_chung " +
       "HAVING COUNT(*) > 3 or count(*)=1")
    List<Object[]> findTrieuChungWithCountGreaterThanSix();
}
