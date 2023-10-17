/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuchungEntity;
/**
 *
 * @author tranm
 */
public interface TrieuChungRepository extends JpaRepository<TrieuchungEntity, Long>{
    @Query(value = "SELECT u FROM trieu_trung u WHERE u.matc=:matc")// trả về list mã bệnh
    List<TrieuchungEntity> getTrieuChungbymatc(@Param("matc") String ma_benh);
    
    @Query("SELECT u FROM trieu_trung u WHERE u.matc=:matc")// trả về duy nhất giá trị mã bệnh 
    public TrieuchungEntity findOne(@Param("matc")String ma_benh);
}
