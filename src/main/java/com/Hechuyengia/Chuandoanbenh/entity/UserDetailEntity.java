/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.entity;

/**
 *
 * @author tranm
 */
import javax.persistence.*;

@Entity
@Table(name = "user_detail")
public class UserDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user") // Thêm annotation @JoinColumn
    private UserEntity user;

    @Column(name = "bang_tot_nghiep_y_khoa")
    private String bangTotNghiepYKhoa;

    @Column(name = "chung_chi_hanh_nghe")
    private String chungChiHanhNghe;

    @Column(name = "chung_nhan_chuyen_khoa")
    private String chungNhanChuyenKhoa;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "hoc_ham")
    private String hoc_ham;
    
    @Column(name = "hoc_vi")
    private String hoc_vi;

    public UserDetailEntity(Long id, UserEntity user, String bangTotNghiepYKhoa, String chungChiHanhNghe, String chungNhanChuyenKhoa, String image, String hoc_ham, String hoc_vi) {
        this.id = id;
        this.user = user;
        this.bangTotNghiepYKhoa = bangTotNghiepYKhoa;
        this.chungChiHanhNghe = chungChiHanhNghe;
        this.chungNhanChuyenKhoa = chungNhanChuyenKhoa;
        this.image = image;
        this.hoc_ham = hoc_ham;
        this.hoc_vi = hoc_vi;
    }


    


    public UserDetailEntity() {
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getBangTotNghiepYKhoa() {
        return bangTotNghiepYKhoa;
    }

    public void setBangTotNghiepYKhoa(String bangTotNghiepYKhoa) {
        this.bangTotNghiepYKhoa = bangTotNghiepYKhoa;
    }

    public String getChungChiHanhNghe() {
        return chungChiHanhNghe;
    }

    public void setChungChiHanhNghe(String chungChiHanhNghe) {
        this.chungChiHanhNghe = chungChiHanhNghe;
    }

    public String getChungNhanChuyenKhoa() {
        return chungNhanChuyenKhoa;
    }

    public void setChungNhanChuyenKhoa(String chungNhanChuyenKhoa) {
        this.chungNhanChuyenKhoa = chungNhanChuyenKhoa;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHoc_ham() {
        return hoc_ham;
    }

    public void setHoc_ham(String hoc_ham) {
        this.hoc_ham = hoc_ham;
    }

    public String getHoc_vi() {
        return hoc_vi;
    }

    public void setHoc_vi(String hoc_vi) {
        this.hoc_vi = hoc_vi;
    }
    
    
    
    
}
