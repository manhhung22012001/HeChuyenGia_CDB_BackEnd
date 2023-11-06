/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.security;

import com.Hechuyengia.Chuandoanbenh.details.CustomUserDetails;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
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

    @PostMapping("/check-user-info")
    public ResponseEntity<UserEntity> checkUserInfo(@RequestBody UserEntity request) {
        String phoneNumberAsString = String.valueOf(request.getPhonenumber());
        boolean isValidUser = userRepository.existsByPhonenumberAndUsername(Integer.parseInt(phoneNumberAsString), request.getUsername());

        UserEntity responseEntity;
        if (isValidUser) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @PostMapping("/reset-password")
//    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
//        // Lưu mật khẩu mới vào cơ sở dữ liệu ở đây
//        // Trả về phản hồi cho phía Angular
//        // Ví dụ: return ResponseEntity.ok("Mật khẩu đã được đặt lại thành công.");
//    }
}
