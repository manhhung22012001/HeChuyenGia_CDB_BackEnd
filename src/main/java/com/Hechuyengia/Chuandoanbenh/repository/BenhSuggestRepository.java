/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.BenhSuggestEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface BenhSuggestRepository extends JpaRepository<BenhSuggestEntity, Long> {
    @Query("SELECT tcb.trieuChungSuggest.ma_trieu_chung_suggest, tcb.trieuChungSuggest.ten_trieu_chung_suggest FROM TrieuChungBenhSuggestEntity tcb WHERE tcb.benhSuggest.ma_benh_suggest = :ma_benh_suggest")
    List<Object[]> findTrieuChungSuggestMoiByMaBenhMoi(@Param("ma_benh_suggest") Long ma_benh_suggest);
}
