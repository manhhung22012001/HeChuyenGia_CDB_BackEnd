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
public class TrieuChungBenhId implements Serializable{
    
    private  int trieuChung;
    private int benh;

    // Constructors
    public TrieuChungBenhId() {
    }

    public TrieuChungBenhId(int trieuChung, int benh) {
        this.trieuChung = trieuChung;
        this.benh = benh;
    }

    // Getters and Setters
    public int getTrieuChung() {
        return trieuChung;
    }

    public void setTrieuChung(int trieuChung) {
        this.trieuChung = trieuChung;
    }

    public int getBenh() {
        return benh;
    }

    public void setBenh(int benh) {
        this.benh = benh;
    }

    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieuChungBenhId that = (TrieuChungBenhId) o;
        return trieuChung == that.trieuChung &&
               benh == that.benh;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trieuChung, benh);
    }

    
    
}
