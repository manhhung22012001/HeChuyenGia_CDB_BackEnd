/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author tranm
 */
@Service
public class BenhService {
    @Autowired
    BenhRepository benhRepository;

    public List<Map<String, Object>> getDS(List<String> tenBenhList) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (String tenBenh : tenBenhList) {
            // Gọi phương thức từ BenhRepository để tìm bệnh có luật theo từng tên bệnh
            List<Object[]> benhDaCoLuat = benhRepository.findBenhByTenBenh(tenBenh);

            for (Object[] benh : benhDaCoLuat) {
                Map<String, Object> benhInfo = new HashMap<>();
                benhInfo.put("ma_benh", benh[0]);
                
                result.add(benhInfo);
            }
        }

        return result;
    }
    
    public List<String> suggestTenBenh(String keyword) {
        return benhRepository.findByTen_benhContainingIgnoreCase(keyword);
    }
}
