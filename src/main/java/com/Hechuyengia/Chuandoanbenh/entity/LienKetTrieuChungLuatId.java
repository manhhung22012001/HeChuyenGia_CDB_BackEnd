/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;
/**
 *
 * @author tranm
 */
public class LienKetTrieuChungLuatId implements Serializable{
    private Long luat;
    private Long trieuChung;

    public LienKetTrieuChungLuatId() {
    }

    public LienKetTrieuChungLuatId(Long luat, Long trieuChung) {
        this.luat = luat;
        this.trieuChung = trieuChung;
    }

    public Long getLuat() {
        return luat;
    }

    public void setLuat(Long luat) {
        this.luat = luat;
    }

    public Long getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(Long trieuChung) {
        this.trieuChung = trieuChung;
    }

    
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LienKetTrieuChungLuatId that = (LienKetTrieuChungLuatId) o;
        return luat == that.luat &&
               trieuChung == that.trieuChung;
    }

    @Override
    public int hashCode() {
        return Objects.hash(luat, trieuChung);
    }
    
}
