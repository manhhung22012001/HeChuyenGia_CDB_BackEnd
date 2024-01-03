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
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface BenhRepository extends JpaRepository<BenhEntity, Long> {
//        @Query("SELECT tc.ten_trieu_chung, tc.ma_trieu_chung " +
//            "FROM TrieuChungEntity tc " +
//            "JOIN TrieuChungBenhEntity tcb ON tc.ma_trieu_chung = tcb.ma_trieu_chung " +
//            "JOIN BenhEntity b ON tcb.ma_benh = b.ma_benh " +
//            "WHERE b.ma_benh = :ma_benh")
//List<String> findTrieuChungByMaBenh(@Param("ma_benh") Long maBenh);

    @Query("SELECT tcb.trieuChung.ma_trieu_chung, tcb.trieuChung.ten_trieu_chung FROM TrieuChungBenhEntity tcb WHERE tcb.benh.ma_benh = :ma_benh")
    List<Object[]> findTrieuChungByMaBenh(@Param("ma_benh") Long maBenh);

    @Query("SELECT b FROM BenhEntity b WHERE b.loai_he = :loai_he")
    List<BenhEntity> findByLoaiHe(@Param("loai_he") String loai_he);

    @Query(value = "SELECT t.ma_benh "
            + "FROM benh t "
            + "LEFT JOIN lien_ket_benh_luat m ON t.ma_benh = m.ma_benh "
            + "WHERE m.ma_luat IS NULL "
            + "AND t.ten_benh = :ten_benh", nativeQuery = true)
    List<Object[]> findBenhByTenBenh(@Param("ten_benh") String ten_benh);//nativeQuery = true,cung cấp một truy vấn SQL thô (native SQL query) cho cơ sở dữ liệu của bạn và Spring Data JPA sẽ thực thi truy vấn đó một cách trực tiếp mà không cần chuyển đổi nó thành JPQL.

    @Query("SELECT tc.ten_benh FROM BenhEntity tc WHERE LOWER(tc.ten_benh) LIKE LOWER(:keyword)")
    public List<String> findByTen_benhContainingIgnoreCase(@Param("keyword") String keyword);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM BenhEntity t WHERE t.ten_benh = :keyword")
    boolean existsByTenBenh(@Param("keyword") String keyword);

    @Query("SELECT t FROM BenhEntity t WHERE t.ma_benh = :ma_benh")
    BenhEntity findByMaBenh(@Param("ma_benh") Long ma_benh);

}
