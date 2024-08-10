package com.web2.kloun.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    
    private final String fileBasePath = "./src/main/resources/static/public/";
    private final Path path;

    public FileStorageService(Environment env) {
        this.path = Paths.get(env.getProperty("file.upload-dir", fileBasePath))
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.path);
        } catch(Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String store(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + getFileExtension(file);
        try {
            Path targetLocation = this.path.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation);
            
        } catch(Exception ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
        return fileName;
    }

    private String getFileExtension(MultipartFile file) {
        String name = file.getOriginalFilename();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }

}

