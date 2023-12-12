/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author tranm
 */
public class TrieuChungBenhSuggestId implements Serializable{
    private Long trieuChungSuggest;
    
    private Long benhSuggest;

    public TrieuChungBenhSuggestId() {
    }

    public TrieuChungBenhSuggestId(Long trieuChungSuggest, Long benhSuggest) {
        this.trieuChungSuggest = trieuChungSuggest;
        this.benhSuggest = benhSuggest;
    }
    
    public Long getTrieuChungSuggest() {
        return trieuChungSuggest;
    }

    public void setTrieuChungSuggest(Long trieuChungSuggest) {
        this.trieuChungSuggest = trieuChungSuggest;
    }

    public Long getBenhSuggest() {
        return benhSuggest;
    }

    public void setBenhSuggest(Long benhSuggest) {
        this.benhSuggest = benhSuggest;
    }
    
    // Equals and HashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrieuChungBenhSuggestId that = (TrieuChungBenhSuggestId) o;
        return trieuChungSuggest == that.trieuChungSuggest &&
               benhSuggest == that.benhSuggest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trieuChungSuggest, benhSuggest);
    }
}
