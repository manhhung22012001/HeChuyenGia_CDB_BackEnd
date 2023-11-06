/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.repository;

import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tranm
 */
public interface UserRepository extends JpaRepository<UserEntity, Long>{
    @Query("select u from UserEntity u where u.username=:username")
    public UserEntity findOne(@Param("username")String id_user);
    boolean existsByUsername(String username); 
    public UserEntity findByUsername(String username);
    
}
