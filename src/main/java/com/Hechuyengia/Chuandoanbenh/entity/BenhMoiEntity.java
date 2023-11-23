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
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import javax.persistence.Table;
/**
 *
 * @author tranm
 */
@Entity
@Table(name = "benh_moi")
public class BenhMoiEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_benh_moi")
    private int ma_benh_moi;
    
    @Column(name = "ten_benh_moi")
    private String ten_benh_moi;
    
    @Column(name = "loai_he")
    private String loai_he;
    
    @Column(name = "trang_thai")
    private String trang_thai; 
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserEntity userEntity; // Định nghĩa mối quan hệ với bảng người dùng

    public BenhMoiEntity() {
    }

    public BenhMoiEntity(int ma_benh_moi, String ten_benh_moi, String loai_he, String trang_thai, UserEntity userEntity) {
        this.ma_benh_moi = ma_benh_moi;
        this.ten_benh_moi = ten_benh_moi;
        this.loai_he = loai_he;
        this.trang_thai = trang_thai;
        this.userEntity = userEntity;
    }

    

   

    public int getMa_benh_moi() {
        return ma_benh_moi;
    }

    public void setMa_benh_moi(int ma_benh_moi) {
        this.ma_benh_moi = ma_benh_moi;
    }

    public String getTen_benh_moi() {
        return ten_benh_moi;
    }

    public void setTen_benh_moi(String ten_benh_moi) {
        this.ten_benh_moi = ten_benh_moi;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public String getLoai_he() {
        return loai_he;
    }

    public void setLoai_he(String loai_he) {
        this.loai_he = loai_he;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
    
    

}
