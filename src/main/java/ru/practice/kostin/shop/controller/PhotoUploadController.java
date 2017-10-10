package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.FileService;

import java.io.IOException;

@Controller
public class PhotoUploadController {

    private FileService fileService;

    @GetMapping("/file")
    public String index() {
        return "upload-form";
    }

    //TODO:надо сделать редирект с картинками после сохранения в базу!
    //TODO:при попытке сохранения файла,размером больше позволенного в пропетях, теряется соединение
    @PostMapping("/upload")
    @ResponseBody
    public String uploadFile(@RequestParam("productId") Integer productId,
                             @RequestParam("file") MultipartFile file)
            throws IOException, UnsupportedExtensionException, FileTooLargeException {
        return fileService.saveFile(file, productId);
    }


    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
