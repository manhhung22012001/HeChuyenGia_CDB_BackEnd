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
@Table(name = "benh")
public class BenhEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_benh")
    private int ma_benh;
    
    @Column(name = "ten_benh")
    private String ten_benh;

    public int getMa_benh() {
        return ma_benh;
    }

    public void setMa_benh(int ma_benh) {
        this.ma_benh = ma_benh;
    }

    public String getTen_benh() {
        return ten_benh;
    }

    public void setTen_benh(String ten_benh) {
        this.ten_benh = ten_benh;
    }

    
    
}
