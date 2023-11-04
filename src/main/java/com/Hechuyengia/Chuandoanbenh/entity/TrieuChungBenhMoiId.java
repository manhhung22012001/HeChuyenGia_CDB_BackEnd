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
public class TrieuChungBenhMoiId implements Serializable{
    private  int trieuChungMoi;
    private int benhMoi;

    public TrieuChungBenhMoiId() {
    }

    public TrieuChungBenhMoiId(int trieuChungMoi, int benhMoi) {
        this.trieuChungMoi = trieuChungMoi;
        this.benhMoi = benhMoi;
    }

    public int getTrieuChungMoi() {
        return trieuChungMoi;
    }

    public void setTrieuChungMoi(int trieuChungMoi) {
        this.trieuChungMoi = trieuChungMoi;
    }

    public int getBenhMoi() {
        return benhMoi;
    }

    public void setBenhMoi(int benhMoi) {
        this.benhMoi = benhMoi;
    }
    
    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieuChungBenhMoiId that = (TrieuChungBenhMoiId) o;
        return trieuChungMoi == that.trieuChungMoi &&
               benhMoi == that.benhMoi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trieuChungMoi, benhMoi);
    }


}
