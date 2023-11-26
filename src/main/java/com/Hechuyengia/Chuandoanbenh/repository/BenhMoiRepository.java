/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.BenhMoiEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface BenhMoiRepository extends JpaRepository<BenhMoiEntity, Long>{
    @Query("SELECT tcb.trieuChungMoi.ma_trieu_chung_moi, tcb.trieuChungMoi.ten_trieu_chung_moi FROM TrieuChungBenhMoiEntity tcb WHERE tcb.benhMoi.ma_benh_moi = :ma_benh_moi")
    List<Object[]> findTrieuChungMoiByMaBenhMoi(@Param("ma_benh_moi") Long ma_benh_moi);
    @Query("SELECT ma_benh_moi FROM BenhMoiEntity WHERE ten_benh_moi=:ten_benh_moi")
    public Long finMaBenhMoiByTenBenhMoi(@Param("ten_benh_moi") String ten_benh_moi);
    
    public Optional<BenhMoiEntity> findById(Long ma_benh_moi);
}
