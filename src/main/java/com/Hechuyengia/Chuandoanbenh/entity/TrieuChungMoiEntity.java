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
@Table(name = "trieu_chung_moi")
public class TrieuChungMoiEntity implements Serializable{
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_trieu_chung_moi")
    private int ma_trieu_chung_moi;
    
    @Column(name = "ten_trieu_chung_moi")
    private String ten_trieu_chung_moi;

    public TrieuChungMoiEntity() {
    }

    public TrieuChungMoiEntity(int ma_trieu_chung_moi, String ten_trieu_chung_moi) {
        this.ma_trieu_chung_moi = ma_trieu_chung_moi;
        this.ten_trieu_chung_moi = ten_trieu_chung_moi;
    }

    public int getMa_trieu_chung_moi() {
        return ma_trieu_chung_moi;
    }

    public void setMa_trieu_chung_moi(int ma_trieu_chung_moi) {
        this.ma_trieu_chung_moi = ma_trieu_chung_moi;
    }

    public String getTen_trieu_chung_moi() {
        return ten_trieu_chung_moi;
    }

    public void setTen_trieu_chung_moi(String ten_trieu_chung_moi) {
        this.ten_trieu_chung_moi = ten_trieu_chung_moi;
    }
    
    
}
