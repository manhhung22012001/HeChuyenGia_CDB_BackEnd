/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
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
@Table(name = "trieu_chung_benh_suggest")
@IdClass(TrieuChungBenhSuggestId.class)
public class TrieuChungBenhSuggestEntity implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_trieu_chung_suggest")
    private TrieuChungSuggestEntity trieuChungSuggest;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_benh_suggest")
    private BenhSuggestEntity benhSuggest;

    public TrieuChungBenhSuggestEntity() {
    }

    public TrieuChungBenhSuggestEntity(TrieuChungSuggestEntity trieuChungSuggest, BenhSuggestEntity benhSuggest) {
        this.trieuChungSuggest = trieuChungSuggest;
        this.benhSuggest = benhSuggest;
    }

    public TrieuChungSuggestEntity getTrieuChungSuggest() {
        return trieuChungSuggest;
    }

    public void setTrieuChungSuggest(TrieuChungSuggestEntity trieuChungSuggest) {
        this.trieuChungSuggest = trieuChungSuggest;
    }

    public BenhSuggestEntity getBenhSuggest() {
        return benhSuggest;
    }

    public void setBenhSuggest(BenhSuggestEntity benhSuggest) {
        this.benhSuggest = benhSuggest;
    }

    
    
    
}
