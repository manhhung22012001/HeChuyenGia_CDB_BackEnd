/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhId;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
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
public interface TrieuChungBenhRepository extends JpaRepository<TrieuChungBenhEntity, TrieuChungBenhId> {

    @Query("SELECT DISTINCT tcb.benh.id "
            + "FROM TrieuChungBenhEntity tcb "
            + "WHERE tcb.trieuChung.id = :ma_trieu_chung "
            + "AND tcb.benh.id <> :ma_benh"
    )
    List<Long> findBenhIdsByTrieuChungList(@Param("ma_trieu_chung") Long ma_trieu_chung, @Param("ma_benh") Long ma_benh);

    @Query("SELECT COUNT(tcb) > 0 "
            + "FROM TrieuChungBenhEntity tcb "
            + "WHERE tcb.benh = :existingBenh AND tcb.trieuChung = :existingTrieuChung")
    boolean existsByBenhAndTrieuChung(@Param("existingBenh") BenhEntity existingBenh,
            @Param("existingTrieuChung") TrieuChungEntity existingTrieuChung);

}
