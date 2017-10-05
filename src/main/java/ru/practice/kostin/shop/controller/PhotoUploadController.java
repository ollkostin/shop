package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.service.ProductPhotoService;

import java.io.IOException;

@Controller
public class PhotoUploadController {

    private ProductPhotoService productPhotoService;

    @GetMapping("/file")
    public String index() {
        return "upload-form";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile file) { //throws UnsupportedExtensionException {
        String path;
        try {
            path = productPhotoService.saveFile(file);
            return path;
        } catch (IOException e) {
            return e.getMessage();
        }
    }


    @Autowired
    public void setProductPhotoService(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }
}
