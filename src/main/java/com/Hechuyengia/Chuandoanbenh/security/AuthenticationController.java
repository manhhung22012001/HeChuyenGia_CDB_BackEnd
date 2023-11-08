/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.security;

import EmailOtpDTO.EmailOtpDTO;
import com.Hechuyengia.Chuandoanbenh.details.CustomUserDetails;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.service.EmailService;
import static EmailOtpDTO.generateOTP.generateOTP;

import com.Hechuyengia.Chuandoanbenh.service.OtpService;
import javax.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author tranm
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    OtpService otpService;
    

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @CrossOrigin
    @PostMapping("/login")
    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword() // Truyền mật khẩu nguyên thể, không mã hóa
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
        return new LoginResponse(jwt, "200");

    }

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<?> post(@RequestBody UserEntity input) {
        System.out.println("Ten" + input.getFullname() + "sđt " + input.getPhonenumber() + "email " + input.getEmail() + "username" + input.getUsername() + "pass" + input.getPassword());
        if (userRepository.existsByUsername(input.getUsername())) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            // Mã hóa mật khẩu trước khi lưu người dùng
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(input.getPassword());
            input.setPassword(encodedPassword);

            userRepository.save(input);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    @CrossOrigin
    @PostMapping("/forgotpass")
    public ResponseEntity<?> checkUserInfo(@RequestBody UserEntity request) {
        //String phoneNumberAsString = String.valueOf(request.getPhonenumber());
        //System.out.println(phoneNumberAsString);
        boolean isValidUser = userRepository.existsByPhonenumberAndUsername(request.getPhonenumber(), request.getUsername());
        //System.out.println(request.getPhonenumber()+"+"+request.getUsername());
        if (isValidUser) {
            // Tạo mã OTP ngẫu nhiên
            String otp = generateOTP();          
            // lấy user trả về
            UserEntity user = userRepository.findByUsername(request.getUsername());
            // Lưu mã OTP vào Redis với email làm key và thời gian hết hạn là 10 phút
            otpService.saveOtp(request.getEmail(), otp, 10);
            System.out.println("OTP IS"+otp);
            // Gửi mã OTP đến email của người dùng
            emailService.sendResetPasswordEmail(request.getEmail(), otp);
            
            // Trả về thành công
            return new ResponseEntity<>(user, HttpStatus.valueOf(200));
        } else {
            // Người dùng không tồn tại, trả về null hoặc một đối tượng UserEntity rỗng tùy theo nhu cầu của bạn
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @CrossOrigin
    @PostMapping("/CheckOTP")
    public ResponseEntity<?> verifyOTP(@RequestBody EmailOtpDTO request) {
        String email = request.getEmail();
        String otp = request.getOtp();
        System.out.println("OTP from request: "+request.getOtp());
        System.out.println("Mail from request: "+request.getEmail());
        // Kiểm tra mã OTP trong Redis
        String storedOTP = otpService.getOtp(email);
        System.out.println("OTP trong redis: "+storedOTP);
        
        if (storedOTP != null && storedOTP.equals(otp)) {
           
            // Trả về thông báo thành công với mã HTTP 200
            
            return new ResponseEntity<>(storedOTP, HttpStatus.valueOf(200));
           
        } else {
            
            // Trả về thông báo lỗi với mã HTTP 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mã OTP không đúng");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody UserEntity request) {
        // Mã hóa mật khẩu trước khi lưu người dùng
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            request.setPassword(encodedPassword);

            userRepository.save(request);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
