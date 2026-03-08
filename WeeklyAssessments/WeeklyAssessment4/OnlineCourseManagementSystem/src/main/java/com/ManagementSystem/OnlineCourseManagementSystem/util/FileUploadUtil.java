
package com.ManagementSystem.OnlineCourseManagementSystem.util;

import com.ManagementSystem.OnlineCourseManagementSystem.exception.FileStorageException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUploadUtil {

    private static final String UPLOAD_DIR = "uploads/";

    // Save file
    public static String saveFile(MultipartFile file) {
        try {
            String fileName = System.currentTimeMillis() + "_" +
                    StringUtils.cleanPath(file.getOriginalFilename());

            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("File upload failed", ex);
        }
    }

    // Load file for download
    public static Resource loadFile(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName).toAbsolutePath();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new FileStorageException("File not found: " + fileName);
            }

            return resource;
        } catch (Exception ex) {
            throw new FileStorageException("File download failed: " + fileName, ex);
        }
    }
}