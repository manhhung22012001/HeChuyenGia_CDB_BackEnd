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
    @Query(value = "SELECT DISTINCT b.Ma_Benh, b.Ten_Benh " +
               "FROM Benh b " +
               "JOIN Lien_Ket_Benh_Luat lbl ON b.Ma_Benh = lbl.Ma_Benh " +
               "JOIN Luat l ON lbl.Ma_Luat = l.Ma_Luat " +
               "JOIN Lien_Ket_Trieu_Chung_Luat ltcl ON l.Ma_Luat = ltcl.Ma_Luat " +
               "JOIN Trieu_Chung tc ON ltcl.Ma_Trieu_Chung = tc.Ma_Trieu_Chung " +
               "WHERE tc.Ma_Trieu_Chung IN :danh_sach_tc " +
               "GROUP BY b.Ma_Benh, b.Ten_Benh " +
               "HAVING COUNT(DISTINCT tc.Ma_Trieu_Chung) = :numSelectedSymptoms", nativeQuery = true)
List<Object[]> findSymptomsInSelectedBenh(@Param("danh_sach_tc") List<String> danh_sach_tc, @Param("numSelectedSymptoms") int numSelectedSymptoms);


}

