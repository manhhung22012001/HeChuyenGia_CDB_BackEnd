/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.DTO;

import java.util.Random;

/**
 *
 * @author tranm
 */
public class generateOTP {
     public static String generateOTP() {
        // Tạo mã OTP ngẫu nhiên, ví dụ, mã có 6 chữ số
        int otpLength = 4;
        StringBuilder otp = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(6)); // Số ngẫu nhiên từ 0 đến 9
        }

        return otp.toString();
    }

   
}
