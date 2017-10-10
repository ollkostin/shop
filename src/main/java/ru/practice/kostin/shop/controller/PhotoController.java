package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.service.FileService;

import java.io.IOException;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class PhotoController {
    private FileService fileService;

    @GetMapping("/product/{id}/{filename}")
    @ResponseBody
    public ResponseEntity getPhoto(@PathVariable Integer id, @PathVariable String filename) throws IOException {
        return ok().body(fileService.getFile(id, filename));
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
