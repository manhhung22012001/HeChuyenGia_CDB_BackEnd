/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * @author tranm
 */
@Entity
@Table(name = "lien_ket_benh_luat")
@IdClass(LienKetBenhLuatId.class)
public class LienKetBenhLuatEntity implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_luat")
    private LuatEntity ma_luat;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_benh")
    private BenhEntity ma_benh;

    public LuatEntity getMa_luat() {
        return ma_luat;
    }

    public void setMa_luat(LuatEntity ma_luat) {
        this.ma_luat = ma_luat;
    }

    public BenhEntity getMa_benh() {
        return ma_benh;
    }

    public void setMa_benh(BenhEntity ma_benh) {
        this.ma_benh = ma_benh;
    }

   
    
    
}
