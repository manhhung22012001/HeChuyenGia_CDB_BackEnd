/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.DTO;

/**
 *
 * @author tranm
 */
public class UserInfoDTO {
     private Long id_user;
    private String username;
    private String fullname;
    private String phonenumber;
    private String role;
    private String email;
    private String status;
    private String bangTotNghiepYKhoa;
    private String chungChiHanhNghe;
    private String chungNhanChuyenKhoa;
    private String image;

    public UserInfoDTO(Long id_user, String username, String fullname, String phonenumber, String role, String email, String status, String bangTotNghiepYKhoa, String chungChiHanhNghe, String chungNhanChuyenKhoa, String image) {
        this.id_user = id_user;
        this.username = username;
        this.fullname = fullname;
        this.phonenumber = phonenumber;
        this.role = role;
        this.email = email;
        this.status = status;
        this.bangTotNghiepYKhoa = bangTotNghiepYKhoa;
        this.chungChiHanhNghe = chungChiHanhNghe;
        this.chungNhanChuyenKhoa = chungNhanChuyenKhoa;
        this.image = image;
    }

    public UserInfoDTO() {
    }
    
    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
}
