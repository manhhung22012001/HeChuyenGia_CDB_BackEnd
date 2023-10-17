/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.Hechuyengia.Chuandoanbenh.entity.BenhEnity;

import java.util.List;
/**
 *
 * @author tranm
 */
public interface BenhRepository extends JpaRepository<BenhEnity, Long>{
    @Query(value = "SELECT u FROM Benh u WHERE u.ma_benh=:ma_benh")// trả về list mã bệnh
    List<BenhEnity> getBenhbymabenh(@Param("ma_benh") String ma_benh);
    
    @Query("SELECT u FROM Benh u WHERE u.ma_benh=:ma_benh")// trả về duy nhất giá trị mã bệnh 
    public BenhEnity findOne(@Param("ma_benh")String ma_benh);
    
}
