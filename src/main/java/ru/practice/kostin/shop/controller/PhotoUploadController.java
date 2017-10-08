package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.ProductPhotoService;

import java.io.IOException;

@Controller
public class PhotoUploadController {

    private ProductPhotoService productPhotoService;

    @GetMapping("/file")
    public String index() {
        return "upload-form";
    }

    //TODO:надо сделать редирект с картинками после сохранения в базу!
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("productId") Integer productId,
                             @RequestParam("file") MultipartFile file) throws IOException, UnsupportedExtensionException {
        return productPhotoService.saveFile(file, productId);
    }


    @Autowired
    public void setProductPhotoService(ProductPhotoService productPhotoService) {
        this.productPhotoService = productPhotoService;
    }
}
