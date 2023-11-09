/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.repository.TrieuChungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @author tranm
 */
@Service
public class TrieuChungService {
     private final TrieuChungRepository trieuchungRepository;

    @Autowired
    public TrieuChungService(TrieuChungRepository trieuchungRepository) {
        this.trieuchungRepository = trieuchungRepository;
    }

    public List<Object[]> getTrieuChungWithCountGreaterThanSix() {
        return trieuchungRepository.findTrieuChungWithCountGreaterThanSix();
    }

   
}
