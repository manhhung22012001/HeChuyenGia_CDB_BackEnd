/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tranm
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select u from UserEntity u where u.username=:username")
    public UserEntity findOne(@Param("username") String id_user);

    boolean existsByUsername(String username);

    public UserEntity findByUsername(String username);

    boolean existsByPhonenumberAndUsernameAndEmail(String phonenumber, String username, String email);

    public boolean existsByEmail(String email);

    public boolean existsByPhonenumber(String phonenumber);

    @Query("SELECT new com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO(u.id_user, u.username, u.fullname, u.phonenumber, u.role, u.email, u.status, u.userDetail.bangTotNghiepYKhoa, u.userDetail.chungChiHanhNghe, u.userDetail.chungNhanChuyenKhoa, u.userDetail.image, u.userDetail.hoc_ham, u.userDetail.hoc_vi) FROM UserEntity u LEFT JOIN u.userDetail WHERE u.id_user = :userId")
    UserInfoDTO getUserInfoById(@Param("userId") Long userId);

    @Query("SELECT ud FROM UserEntity ud WHERE ud.id_user = :userId")
    UserEntity findByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(u) FROM UserEntity u WHERE u.status = '0'")
    Long countByStatusEqualsZero();
}
