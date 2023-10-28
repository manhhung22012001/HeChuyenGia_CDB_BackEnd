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
@Table(name = "lien_ket_trieu_chung_luat")
@IdClass(LienKetTrieuChungLuatId.class)
public class LienKetTrieuChungLuatEntity implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_luat")
    private LuatEntity luat;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_trieu_chung")
    private TrieuChungEntity trieuChung;

    public LuatEntity getLuat() {
        return luat;
    }

    public void setLuat(LuatEntity luat) {
        this.luat = luat;
    }

    public TrieuChungEntity getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(TrieuChungEntity trieuChung) {
        this.trieuChung = trieuChung;
    }

   

    
    
    
}
