package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("/api/products/{productId}/photos")
public class PhotoController {
    private FileService fileService;

    @GetMapping("/{fileId}")
    public void getPhoto(@PathVariable Integer productId, @PathVariable String fileId, HttpServletResponse response) throws IOException {
        File file = fileService.getFileByProductIdAndFilename(productId, fileId);
        if (!file.exists()) {
            file = fileService.getPlaceholderImage();
        }
        try (OutputStream stream = response.getOutputStream()) {
            fileService.readFileToOutputStream(file, stream);
            fileService.setResponseContentImageHeaders(file, response);
        }
    }

    @PostMapping("/")
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
