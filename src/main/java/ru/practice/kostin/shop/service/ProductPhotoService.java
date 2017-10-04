package ru.practice.kostin.shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ProductPhotoService {

    @Value("${custom.image-directory-path}")
    private String fileDirectoryPath;

    public void saveFile(MultipartFile file) throws IOException {
        File file1 = new File(fileDirectoryPath, file.getOriginalFilename());
        if (!file1.createNewFile()){
            throw new IOException("");
        }
        FileOutputStream stream = new FileOutputStream(file1.getPath());
        stream.write(file.getBytes());
    }
}
