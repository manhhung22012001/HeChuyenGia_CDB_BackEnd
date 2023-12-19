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
@Table(name = "luat")
public class LuatEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_luat")
    private Long ma_luat;
    
    @Column(name = "ten_luat")
    private String ten_luat;
    
    @Column(name = "loai_luat")
    private Long loai_luat;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity; // Định nghĩa mối quan hệ với bảng người dùng

    public LuatEntity(Long ma_luat, String ten_luat, Long loai_luat, UserEntity userEntity) {
        this.ma_luat = ma_luat;
        this.ten_luat = ten_luat;
        this.loai_luat = loai_luat;
        this.userEntity = userEntity;
    }

  

    public LuatEntity() {
    }
    
    public Long getMa_luat() {
        return ma_luat;
    }

    public void setMa_luat(Long ma_luat) {
        this.ma_luat = ma_luat;
    }

    public String getTen_luat() {
        return ten_luat;
    }

    public void setTen_luat(String ten_luat) {
        this.ten_luat = ten_luat;
    }

    public Long getLoai_luat() {
        return loai_luat;
    }

    public void setLoai_luat(Long loai_luat) {
        this.loai_luat = loai_luat;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

   

    
    
    
}
