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
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface TrieuChungRepository extends JpaRepository<TrieuChungEntity, Long> {

    @Query("SELECT tc.ma_trieu_chung, tc.ten_trieu_chung, COUNT(*) "
            + "FROM TrieuChungBenhEntity tcb "
            + "JOIN tcb.trieuChung tc "
            + "GROUP BY tc.ma_trieu_chung, tc.ten_trieu_chung "
            + "HAVING COUNT(*) > 3 ")
    List<Object[]> findTrieuChungWithCountGreaterThanSix();

    @Query("SELECT tc.ten_trieu_chung FROM TrieuChungEntity tc WHERE LOWER(tc.ten_trieu_chung) LIKE LOWER(:keyword)")
    public List<String> findByTen_trieu_chungContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT tc.ma_trieu_chung, tc.ten_trieu_chung, COUNT(*) "
            + "FROM TrieuChungBenhEntity tcb "
            + "JOIN tcb.trieuChung tc "
            + "GROUP BY tc.ma_trieu_chung, tc.ten_trieu_chung "
            + "HAVING COUNT(*)=1 ")
    List<Object[]> findTrieuChungonly();

    @Query("SELECT tc FROM TrieuChungEntity tc WHERE tc.ten_trieu_chung = :ten_trieu_chung")
    public Optional<TrieuChungEntity> findByTenTrieuChung(@Param("ten_trieu_chung") String ten_trieu_chung);

    @Query("SELECT tc.ma_trieu_chung FROM TrieuChungEntity tc WHERE tc.ten_trieu_chung IN :ten_trieu_chung")
    public List<Long> findMaTrieuChungByTenTrieuChungIn(List<String> ten_trieu_chung);
    
    
    @Query("SELECT tc.ma_trieu_chung FROM TrieuChungEntity tc WHERE tc.ten_trieu_chung = :ten_trieu_chung")
    public Long findMaTrieuChungByTenTrieuChung(@Param("ten_trieu_chung") String ten_trieu_chung);

}
