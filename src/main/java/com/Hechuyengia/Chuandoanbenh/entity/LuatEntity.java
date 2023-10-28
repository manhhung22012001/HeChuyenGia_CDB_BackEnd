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
@Table(name = "luat")
public class LuatEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_luat")
    private int ma_luat;
    
    @Column(name = "ten_luat")
    private String ten_luat;
    
    @Column(name = "loai_luat")
    private int loai_luat;

    public int getMa_luat() {
        return ma_luat;
    }

    public void setMa_luat(int ma_luat) {
        this.ma_luat = ma_luat;
    }

    public String getTen_luat() {
        return ten_luat;
    }

    public void setTen_luat(String ten_luat) {
        this.ten_luat = ten_luat;
    }

    public int getLoai_luat() {
        return loai_luat;
    }

    public void setLoai_luat(int loai_luat) {
        this.loai_luat = loai_luat;
    }

    
    
    
}
