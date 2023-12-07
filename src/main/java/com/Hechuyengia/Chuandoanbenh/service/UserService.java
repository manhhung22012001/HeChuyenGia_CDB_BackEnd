/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.DTO.UserInfoDTO;
import com.Hechuyengia.Chuandoanbenh.details.CustomUserDetails;
import com.Hechuyengia.Chuandoanbenh.entity.UserDetailEntity;
import com.Hechuyengia.Chuandoanbenh.entity.UserEntity;
import com.Hechuyengia.Chuandoanbenh.repository.UserDetailRepository;
import com.Hechuyengia.Chuandoanbenh.repository.UserRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tranm
 */
@Service
public class UserService implements UserDetailsService {

    @Value("${uploadPath}")
    private String uploadPath;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository repo;
    

    @Autowired
    private UserDetailRepository userDetailRepository;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void save(UserEntity user) {
        repo.save(user);
    }

    public List<UserEntity> listAll() {
        return (List<UserEntity>) repo.findAll();
    }

    public UserEntity get(Long id) {
        return repo.findById(id).get();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void saveUser(UserEntity user) {
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);
        repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = repo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new CustomUserDetails(user); // CustomUserDetails là implement của UserDetails với các thông tin cần thiết của người dùng
    }

    public UserInfoDTO getUserInfo(Long userId) {
        return repo.getUserInfoById(userId);
    }

    public String saveUserDetailsAndFiles(Long userId, MultipartFile anhdaidien, MultipartFile bangTotNghiepYKhoa, MultipartFile chungChiHanhNghe, MultipartFile chungNhanChuyenKhoa, String hoc_ham, String hoc_vi, String status) {
        UserEntity user = repo.findById(userId).orElse(null);

        if (user != null) {
            try {
                // Lưu các file vào thư mục upload
                String anhdaidienPath = saveFile(anhdaidien);
                String bangTotNghiepYKhoaPath = saveFile(bangTotNghiepYKhoa);
                String chungChiHanhNghePath = saveFile(chungChiHanhNghe);
                String chungNhanChuyenKhoaPath = saveFile(chungNhanChuyenKhoa);

                // Lưu thông tin vào bảng UserDetailEntity
                UserDetailEntity userDetail = new UserDetailEntity();
                userDetail.setUser(user);
                userDetail.setBangTotNghiepYKhoa(bangTotNghiepYKhoaPath);
                userDetail.setChungChiHanhNghe(chungChiHanhNghePath);
                userDetail.setChungNhanChuyenKhoa(chungNhanChuyenKhoaPath);
                userDetail.setImage(anhdaidienPath);
                userDetail.setHoc_vi(hoc_vi);
                userDetail.setHoc_ham(hoc_ham);

                userDetailRepository.save(userDetail);

                Optional<UserEntity> existingUser = repo.findById(userId);
                if (existingUser.isPresent()) {
                    UserEntity userToUpdate = existingUser.get();
                    userToUpdate.setStatus(status);
                    UserEntity savedUser = repo.save(userToUpdate);

                }

                return "User details and files saved successfully!";
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to save files!";
            }
        } else {
            return "User not found!";
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            String fullPath = uploadPath + File.separator + fileName;

            // Sử dụng Resource để tạo đường dẫn lưu trữ
            Resource resource = new UrlResource("file:" + fullPath);

            // Kiểm tra xem thư mục lưu trữ đã tồn tại chưa, nếu chưa thì tạo mới
            if (!resource.exists()) {
                File dest = resource.getFile();
                dest.getParentFile().mkdirs();
            }

            // Sao chép file vào thư mục lưu trữ
            FileCopyUtils.copy(file.getBytes(), resource.getFile());

            // Trả về đường dẫn lưu trữ của file
            return fullPath;
        }
        return null;
    }

    public UserDetailEntity getUserDetail(Long userId) {
        return userDetailRepository.findByUserId(userId);
    }
    public UserEntity getUserEntity(Long userId) {
        return repo.findByUserId(userId);
    }
}
