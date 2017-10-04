package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.service.ProductPhotoService;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.ok;

@Controller
public class PhotoUploadController {

    private ProductPhotoService productPhotoService;

    @PostMapping("/file/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            productPhotoService.saveFile(file);
        } catch (IOException e) {
            return badRequest().body(e.getMessage());
        }
        return ok().build();
    }


    @Autowired
    public void setProductPhotoService(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }
}
