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
    private int luat;
    private int maBenh;
    // contructer

    public LienKetBenhLuatId() {
    }

    public LienKetBenhLuatId(int luat, int maBenh) {
        this.luat = luat;
        this.maBenh = maBenh;
    }

    public int getLuat() {
        return luat;
    }

    public void setLuat(int luat) {
        this.luat = luat;
    }

    public int getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(int maBenh) {
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
