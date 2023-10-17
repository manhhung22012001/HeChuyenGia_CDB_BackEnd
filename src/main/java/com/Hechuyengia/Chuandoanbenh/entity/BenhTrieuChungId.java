/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

/**
 *
 * @author tranm
 */
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class BenhTrieuChungId implements Serializable{
    private int maBenh;
    private int maTC;

    public int getMaBenh() {
        return maBenh;
    }

    public void setMaBenh(int maBenh) {
        this.maBenh = maBenh;
    }

    public int getMaTC() {
        return maTC;
    }

    public void setMaTC(int maTC) {
        this.maTC = maTC;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BenhTrieuChungId)) return false;
        BenhTrieuChungId that = (BenhTrieuChungId) o;
        return maBenh == that.maBenh &&
                maTC == that.maTC;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maBenh, maTC);
    }
}
