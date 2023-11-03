/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.service.DiagnosisService;
import com.Hechuyengia.Chuandoanbenh.service.TrieuChungService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
    @PostMapping("/search1") // 
    public ResponseEntity<List<Object[]>> getTrieuChungWithCountGreaterThanSix() {
        List<Object[]> trieuchungResults = trieuchungService.getTrieuChungWithCountGreaterThanSix();
        return ResponseEntity.ok(trieuchungResults);
    }
    
    @CrossOrigin
    @PostMapping("/search2")
    public ResponseEntity<List<TrieuChungEntity>> searchDiagnosis(@RequestBody List<String> selectedSymptomCodes) {
        System.out.println("mang nhan duoc "+ selectedSymptomCodes);
        List<TrieuChungEntity> symptomsInSelectedLuats = diagnosisService.getSymptomsInSelectedLuats(selectedSymptomCodes);
        return ResponseEntity.ok(symptomsInSelectedLuats);
    }
    @CrossOrigin
    @PostMapping("/ketqua")
     public ResponseEntity<List<BenhEntity>> KQ_cdb(@RequestBody List<String> danh_sach_tc) {
        System.out.println("mang nhan duoc "+ danh_sach_tc);
        List<BenhEntity> findSymptomsInSelectedBenh = diagnosisService.getSymptomsInSelectedBenh(danh_sach_tc);
        return ResponseEntity.ok(findSymptomsInSelectedBenh);
        
    }
     @CrossOrigin
    @PostMapping("/ketqua1")
    public ResponseEntity<List<BenhEntity>> KQ_cdb1(@RequestBody List<String> danh_sach_tc) {
        List<BenhEntity> findSymptomsInSelectedBenh1 = diagnosisService.getSymptomsInSelectedBenh1(danh_sach_tc);
        return ResponseEntity.ok(findSymptomsInSelectedBenh1);
        
    }
     
}