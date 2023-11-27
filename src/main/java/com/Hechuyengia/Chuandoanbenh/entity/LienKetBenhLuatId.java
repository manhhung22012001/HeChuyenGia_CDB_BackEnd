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

public class LienKetBenhLuatId implements Serializable{
    private Long luat;
    private Long maBenh;
    // contructer

    public LienKetBenhLuatId() {
    }

    public LienKetBenhLuatId(Long luat, Long maBenh) {
        this.luat = luat;
        this.maBenh = maBenh;
    }

    public Long getLuat() {
        return luat;
    }

    public void setLuat(Long luat) {
        this.luat = luat;
    }

    public Long getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(Long maBenh) {
        this.maBenh = maBenh;
    }

       

    
    
    
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LienKetBenhLuatId that = (LienKetBenhLuatId) o;
        return luat == that.luat &&
               maBenh == that.maBenh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(luat, maBenh);
    }
}
