/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import com.Hechuyengia.Chuandoanbenh.service.DiagnosisService;
import com.Hechuyengia.Chuandoanbenh.service.TrieuChungService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author tranm
 */

@RestController
@RequestMapping("/diagnosis")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }
    @Autowired
    private TrieuChungService trieuchungService;
    @Autowired
    BenhRepository benhRepository;
    @CrossOrigin
    @PostMapping("/search1") // Api triệu chứng cơ bản xuất hiện nhiều
    public ResponseEntity<List<Object[]>> getTrieuChungWithCountGreaterThanSix() {
        List<Object[]> trieuchungResults = trieuchungService.getTrieuChungWithCountGreaterThanSix();
        return ResponseEntity.ok(trieuchungResults);
    }
    @CrossOrigin
    @PostMapping("/search11") // Api lấy các triệu chứng đặc biệt
    public ResponseEntity<List<Object[]>> getTrieuChungonly() {
        List<Object[]> trieuchungResults = trieuchungService.getTrieuChungonly();
        return ResponseEntity.ok(trieuchungResults);
    }
    @CrossOrigin
    @PostMapping("/search2")// Api nhận mã TC and lấy các triệu chứng của có cùng trong luật with tc đó
    public ResponseEntity<List<TrieuChungEntity>> searchDiagnosis(@RequestBody List<String> selectedSymptomCodes) {
        //System.out.println("mang nhan duoc "+ selectedSymptomCodes);
        List<TrieuChungEntity> symptomsInSelectedLuats = diagnosisService.getSymptomsInSelectedLuats(selectedSymptomCodes);
        return ResponseEntity.ok(symptomsInSelectedLuats);
    }
    @CrossOrigin
    @PostMapping("/ketqua")// API trả về kq chẩn đoán nếu không chọn tc đặc biệt
     public ResponseEntity<List<BenhEntity>> KQ_cdb(@RequestBody List<String> danh_sach_tc) {
        //System.out.println("mang nhan duoc "+ danh_sach_tc);
        List<BenhEntity> findSymptomsInSelectedBenh = diagnosisService.getSymptomsInSelectedBenh(danh_sach_tc);
        return ResponseEntity.ok(findSymptomsInSelectedBenh);
        
    }
     @CrossOrigin
    @PostMapping("/ketqua1")// API trả về kq chẩn đoán nếu chọn tc đặc biệt
    public ResponseEntity<List<BenhEntity>> KQ_cdb1(@RequestBody List<String> danh_sach_tc) {
        List<BenhEntity> findSymptomsInSelectedBenh1 = diagnosisService.getSymptomsInSelectedBenh1(danh_sach_tc);
        return ResponseEntity.ok(findSymptomsInSelectedBenh1);
        
    }
    @CrossOrigin
    @GetMapping("/trieuchung/{ma_benh}") // tìm triệu chứng bởi mã bệnh. 
    public ResponseEntity<List<Object[]>> getTrieuChungByMaBenh(@PathVariable Long ma_benh) {
        
        List<Object[]> trieuchung = benhRepository.findTrieuChungByMaBenh(ma_benh);
        if (trieuchung != null && !trieuchung.isEmpty()) {
            return ResponseEntity.ok(trieuchung);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
     
}