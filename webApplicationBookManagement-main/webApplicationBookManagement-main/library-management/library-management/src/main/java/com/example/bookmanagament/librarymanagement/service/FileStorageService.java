package com.example.bookmanagament.librarymanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService {

    private final Path uploadDir = Paths.get("./src/main/resources/static/uploads");

    public String storeFile(MultipartFile file) {
        try {
            // Kiểm tra loại file
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image")) {
                throw new RuntimeException("Only image files are allowed.");
            }

            // Tạo tên file ngẫu nhiên
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String randomFileName = System.currentTimeMillis() + "_" + (int) (Math.random() * 1000) + extension;

            // Lưu file
            Files.createDirectories(uploadDir);
            Path targetPath = uploadDir.resolve(randomFileName);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image saved at: " + targetPath.toString());

            return randomFileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
