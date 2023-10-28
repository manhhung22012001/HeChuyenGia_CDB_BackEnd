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
import com.Hechuyengia.Chuandoanbenh.entity.TrieuChungBenhId;
/**
 *
 * @author tranm
 */
@Entity
@Table(name = "trieu_chung_benh")
@IdClass(TrieuChungBenhId.class) // Định nghĩa lớp IdClass
public class TrieuChungBenhEntity implements Serializable{
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_trieu_chung")
    private TrieuChungEntity trieuChung;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_benh")
    private BenhEntity benh;

    public TrieuChungEntity getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(TrieuChungEntity trieuChung) {
        this.trieuChung = trieuChung;
    }

    public BenhEntity getBenh() {
        return benh;
    }

    public void setBenh(BenhEntity benh) {
        this.benh = benh;
    }

   
    
    
    
}
