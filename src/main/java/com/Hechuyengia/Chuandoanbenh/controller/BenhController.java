/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.controller;

import com.Hechuyengia.Chuandoanbenh.entity.BenhEntity;
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungEntity;
import com.Hechuyengia.Chuandoanbenh.repository.BenhRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tranm
 */
@RestController
@RequestMapping("/taskbar-cg")
public class BenhController {

    @Autowired
    BenhRepository benhRepository;

    @CrossOrigin
    @GetMapping("/getall")
    public List<BenhEntity> list() {
        return benhRepository.findAll();
    }

//    @CrossOrigin
//    @GetMapping("/trieuchung/{ma_benh}")
//    public ResponseEntity<List<String>> getTrieuChungByMaBenh(@PathVariable int ma_benh) {
//        System.out.println("x=" + ma_benh);
//        List<String> trieuchung = benhRepository.findTrieuChungByMaBenh(ma_benh);
//        if (trieuchung != null && !trieuchung.isEmpty()) {
//            return ResponseEntity.ok(trieuchung);
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
     @CrossOrigin
    @GetMapping("/trieuchung/{ma_benh}")
    public ResponseEntity<List<Object[]>> getTrieuChungByMaBenh(@PathVariable int ma_benh) {
        List<Object[]> trieuchung = benhRepository.findTrieuChungByMaBenh(ma_benh);
        if (trieuchung != null && !trieuchung.isEmpty()) {
            return ResponseEntity.ok(trieuchung);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
