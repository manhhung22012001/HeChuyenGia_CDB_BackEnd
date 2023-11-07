/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
/**
 *
 * @author tranm
 */

@Service
public class OtpService {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public OtpService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveOtp(String email, String otp, long expiryTimeInMinutes) {
        String key = "OTP:" + email;
        redisTemplate.opsForValue().set(key, otp, expiryTimeInMinutes, TimeUnit.MINUTES);
    }

    public String getOtp(String email) {
        String key = "OTP:" + email;
        return redisTemplate.opsForValue().get(key);
    }
}
