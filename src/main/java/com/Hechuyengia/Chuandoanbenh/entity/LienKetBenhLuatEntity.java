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
    private LuatEntity luat;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_benh")
    private BenhEntity maBenh;

    public LuatEntity getLuat() {
        return luat;
    }

    public void setLuat(LuatEntity luat) {
        this.luat = luat;
    }

    public BenhEntity getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(BenhEntity maBenh) {
        this.maBenh = maBenh;
    }

    

   
    
    
}
