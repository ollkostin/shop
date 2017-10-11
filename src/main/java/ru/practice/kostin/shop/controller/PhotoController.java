package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.FileService;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/products/{productId}/photos")
public class PhotoController {
    private FileService fileService;

    @GetMapping("/{fileId}")
    public ResponseEntity getPhoto(@PathVariable Integer productId, @PathVariable String fileId) throws IOException, FileTooLargeException {
        byte[] file = fileService.getFile(productId, fileId);
        if (file == null) {
            return notFound().build();
        }
        return ok().body(file);
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
