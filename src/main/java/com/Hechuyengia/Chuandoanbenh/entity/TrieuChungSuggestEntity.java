/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author tranm
 */
@Entity
@Table(name = "trieu_chung_suggest")
public class TrieuChungSuggestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_trieu_chung_suggest")
    private Long ma_trieu_chung_suggest;

    @Column(name = "ten_trieu_chung_suggest")
    private String ten_trieu_chung_suggest;

    public TrieuChungSuggestEntity() {
    }

    public TrieuChungSuggestEntity(Long ma_trieu_chung_suggest, String ten_trieu_chung_suggest) {
        this.ma_trieu_chung_suggest = ma_trieu_chung_suggest;
        this.ten_trieu_chung_suggest = ten_trieu_chung_suggest;
    }

    public Long getMa_trieu_chung_suggest() {
        return ma_trieu_chung_suggest;
    }

    public void setMa_trieu_chung_suggest(Long ma_trieu_chung_suggest) {
        this.ma_trieu_chung_suggest = ma_trieu_chung_suggest;
    }

    public String getTen_trieu_chung_suggest() {
        return ten_trieu_chung_suggest;
    }

    public void setTen_trieu_chung_suggest(String ten_trieu_chung_suggest) {
        this.ten_trieu_chung_suggest = ten_trieu_chung_suggest;
    }

}
