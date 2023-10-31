package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.LuatEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LuatRepository extends JpaRepository<LuatEntity, Integer> {
    
   @Query(value = "SELECT DISTINCT tc.ma_trieu_chung, tc.ten_trieu_chung " +
               "FROM trieu_chung tc " +
               "JOIN lien_ket_trieu_chung_luat ltcl ON tc.ma_trieu_chung = ltcl.ma_trieu_chung " +
               "WHERE ltcl.ma_luat IN (SELECT ltcl.ma_luat FROM lien_ket_trieu_chung_luat ltcl WHERE ltcl.ma_trieu_chung IN :selectedSymptomCodes )", nativeQuery = true)
List<Object[]> findSymptomsInSelectedLuats(@Param("selectedSymptomCodes") List<String> selectedSymptomCodes);

}

