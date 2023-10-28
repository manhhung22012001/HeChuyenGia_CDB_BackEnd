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
    private int ma_luat;
    private int ma_benh;
    // contructer

    public LienKetBenhLuatId() {
    }

    public LienKetBenhLuatId(int ma_luat, int ma_benh) {
        this.ma_luat = ma_luat;
        this.ma_benh = ma_benh;
    }

    public int getMa_luat() {
        return ma_luat;
    }

    public void setMa_luat(int ma_luat) {
        this.ma_luat = ma_luat;
    }

    public int getMa_benh() {
        return ma_benh;
    }

    public void setMa_benh(int ma_benh) {
        this.ma_benh = ma_benh;
    }

    
    
    
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LienKetBenhLuatId that = (LienKetBenhLuatId) o;
        return ma_luat == that.ma_luat &&
               ma_benh == that.ma_benh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ma_luat, ma_benh);
    }
}
