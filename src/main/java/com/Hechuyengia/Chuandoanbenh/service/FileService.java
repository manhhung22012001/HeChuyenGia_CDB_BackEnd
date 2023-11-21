package com.Hechuyengia.Chuandoanbenh.service;

import com.Hechuyengia.Chuandoanbenh.entity.UserDetailEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Value("${uploadPath}")
    private String uploadPath;

    public byte[] readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return Files.readAllBytes(path);
    }

    public void deleteFile(String filePath) throws IOException {
        Path path = Paths.get(uploadPath, filePath);
        Files.deleteIfExists(path);
    }

    private String getFileType(String filePath) throws IOException {
        try (InputStream is = new FileInputStream(filePath)) {
            byte[] signature = new byte[4]; // Đọc 4 byte đầu tiên của file

            int bytesRead = is.read(signature, 0, 4);
            if (bytesRead >= 4) {
                // Kiểm tra các chữ ký đặc trưng của các loại file thông qua byte signature
                if (signature[0] == (byte) 0xFF && signature[1] == (byte) 0xD8 && signature[2] == (byte) 0xFF) {
                    return "image/jpeg"; // Đây có thể là file JPEG
                } else if (signature[0] == (byte) 0x89 && signature[1] == (byte) 0x50 && signature[2] == (byte) 0x4E && signature[3] == (byte) 0x47) {
                    return "image/png"; // Đây có thể là file PNG
                } else if (signature[0] == (byte) 0x25 && signature[1] == (byte) 0x50 && signature[2] == (byte) 0x44 && signature[3] == (byte) 0x46) {
                    return "application/pdf"; // Đây có thể là file PDF
                }
                // Thêm các kiểm tra cho các loại file khác ở đây (đọc thêm về chữ ký đặc trưng của các loại file)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "unknown"; // Trả về 'unknown' nếu không nhận diện được loại file
    }

    public void addFileToResponse(String fileName, String filePath, Map<String, String> responseBody) {
        try {
            if (filePath != null && !filePath.isEmpty()) {
                Resource resource = new UrlResource("file:" + filePath);
                if (resource.exists()) {
                    File file = resource.getFile();

                    String fileType = getFileType(filePath); // Xác định loại file

                    byte[] fileBytes = Files.readAllBytes(file.toPath());
                    String base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);

                    responseBody.put(fileName, base64EncodedFile + ":" + fileType); // Thêm thông tin về loại file vào chuỗi Base64
                    // In ra mã Base64 của file
                    //System.out.println("Base64 của file " + fileName + ": " + base64EncodedFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Các phương thức khác tùy thuộc vào yêu cầu của ứng dụng
    // Ví dụ: ghi dữ liệu vào tệp tin, tạo thư mục, kiểm tra sự tồn tại của tệp tin, v.v.
}
