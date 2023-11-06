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

//    @CrossOrigin
//    @PostMapping("/login")
//    public ResponseEntity<?> login( // Đây là phản hồi được trả về từ API.
//            @RequestParam(value = "username", defaultValue = "") String username,// Sử dụng để chấp nhận các tham số từ yêu cầu HTTP. Trong trường hợp này, bạn chấp nhận username và password từ yêu cầu POST.
//            @RequestParam(value = "password", defaultValue = "") String password) {
//        UserEntity user = userRepository.findOne(username);
//        if (user != null) {
//            String fullname = user.getFullname();
//            return new ResponseEntity<>(user, HttpStatus.valueOf(200));
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.valueOf(404));
//        }
//    }
//    @CrossOrigin
//    @PostMapping("/login")
//    public LoginResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        try {
//            String encodedPassword = passwordEncoder.encode(loginRequest.getPassword());
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequest.getUsername(),
//                            encodedPassword
//                    )
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
//            return new LoginResponse(jwt,"200");
//        } catch (AuthenticationException ex) {
//          
//          return new LoginResponse(null, "Xác thực không thành công");
//        }
//
//    }
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

//   @CrossOrigin
//   @PostMapping("/diagnostic") // 
//    public ResponseEntity<List<Object[]>> getTrieuChungWithCountGreaterThanSix() {
//        List<Object[]> trieuchungResults = trieuchungService.getTrieuChungWithCountGreaterThanSix();
//        return ResponseEntity.ok(trieuchungResults);
//    }
//     
}
