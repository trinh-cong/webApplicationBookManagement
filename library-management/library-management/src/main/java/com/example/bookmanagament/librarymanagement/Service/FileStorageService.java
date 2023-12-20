package com.example.bookmanagament.librarymanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService {
    private final String uploadDir = "./src/main/resources/static/uploads";

    public String storeFile(MultipartFile file) {
        try {
            Files.createDirectories(Path.of(uploadDir));
            Path targetPath = Path.of(uploadDir, file.getOriginalFilename());
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Image saved at: " + targetPath.toString());

            return targetPath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}