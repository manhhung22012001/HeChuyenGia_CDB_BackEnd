/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.details;

import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author tranm
 */
public class CustomUserDetails implements UserDetails {

    public UserEntity userEnity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = userEnity.getRole();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (role.equals("1")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CHUYEN_GIA"));
        } else if (role.equals("2")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_KY_SU"));
        } else if (role.equals("3")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_QUAN_TRI_VIEN"));
        }

        
        // authorities.add(new SimpleGrantedAuthority("ROLE_OTHER_ROLE"));
        return authorities;

    }

    public UserEntity getUserEnity() {
        return userEnity;
    }

    public CustomUserDetails() {
    }

    public CustomUserDetails(UserEntity userEnity) {
        this.userEnity = userEnity;
    }
    
    public void setUserEnity(UserEntity userEnity) {
        this.userEnity = userEnity;
    }
    
    @Override
    public String getPassword() {
        return userEnity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEnity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
