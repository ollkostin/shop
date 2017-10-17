package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/products/{productId}/photos")
public class PhotoController {
    private FileService fileService;

    /**
     * Gets photo of the product from file system into response output stream.
     * If image was not found, returns placeholder image.
     *
     * @param productId product id
     * @param fileId    file id
     * @param response  http response
     * @throws IOException
     */
    @GetMapping("/{fileId}")
    public void getPhoto(@PathVariable Integer productId, @PathVariable String fileId, HttpServletResponse response) throws IOException {
        File file = fileService.getFileByProductIdAndFilename(productId, fileId);
        response.setStatus(HttpStatus.OK.value());
        if (!file.exists()) {
            file = fileService.getPlaceholderImage();
        }
        fileService.setResponseContentImageHeaders(file, response);
        fileService.readFileIntoResponse(file, response);
    }

    /**
     * Uploads photo of the product
     *
     * @param productId product id
     * @param file      file in multipart form data format
     * @return name of file
     * @throws IOException
     * @throws UnsupportedExtensionException when file is not supported image extension
     * @throws FileTooLargeException         when file size is bigger than allowed
     */
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
