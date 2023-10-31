/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author tranm
 */
@Entity
@Table(name = "trieu_chung")
public class TrieuChungEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_trieu_chung")
    private int ma_trieu_chung;
    
    @Column(name = "ten_trieu_chung")
    private String ten_trieu_chung;

    public TrieuChungEntity() {
    }

    public TrieuChungEntity(int ma_trieu_chung, String ten_trieu_chung) {
        this.ma_trieu_chung = ma_trieu_chung;
        this.ten_trieu_chung = ten_trieu_chung;
    }
    
    
    public int getMa_trieu_chung() {
        return ma_trieu_chung;
    }

    public void setMa_trieu_chung(int ma_trieu_chung) {
        this.ma_trieu_chung = ma_trieu_chung;
    }

    public String getTen_trieu_chung() {
        return ten_trieu_chung;
    }

    public void setTen_trieu_chung(String ten_trieu_chung) {
        this.ten_trieu_chung = ten_trieu_chung;
    }

    

    
    
}
