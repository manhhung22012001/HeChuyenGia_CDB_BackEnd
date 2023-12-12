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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author tranm
 */
@Entity
@Table(name = "benh_suggest")
public class BenhSuggestEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_benh_suggest")
    private Long ma_benh_suggest;

    @Column(name = "ten_benh_suggest")
    private String ten_benh_suggest;

    @Column(name = "trang_thai")
    private Long trang_thai;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity; // Định nghĩa mối quan hệ với bảng người dùng
    
    @Column(name = "ma_benh_trong_bang_benh")
    private Long ma_benh_trong_bang_benh;

    public BenhSuggestEntity() {
    }

    public BenhSuggestEntity(Long ma_benh_suggest, String ten_benh_suggest, Long trang_thai, UserEntity userEntity, Long ma_benh_trong_bang_benh) {
        this.ma_benh_suggest = ma_benh_suggest;
        this.ten_benh_suggest = ten_benh_suggest;
        this.trang_thai = trang_thai;
        this.userEntity = userEntity;
        this.ma_benh_trong_bang_benh = ma_benh_trong_bang_benh;
    }

   

    public Long getMa_benh_suggest() {
        return ma_benh_suggest;
    }

    public void setMa_benh_suggest(Long ma_benh_suggest) {
        this.ma_benh_suggest = ma_benh_suggest;
    }

    public String getTen_benh_suggest() {
        return ten_benh_suggest;
    }

    public void setTen_benh_suggest(String ten_benh_suggest) {
        this.ten_benh_suggest = ten_benh_suggest;
    }

    public Long getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(Long trang_thai) {
        this.trang_thai = trang_thai;
    }

    

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public Long getMa_benh_trong_bang_benh() {
        return ma_benh_trong_bang_benh;
    }

    public void setMa_benh_trong_bang_benh(Long ma_benh_trong_bang_benh) {
        this.ma_benh_trong_bang_benh = ma_benh_trong_bang_benh;
    }
    
    
}
