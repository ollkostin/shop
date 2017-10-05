package ru.practice.kostin.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.util.FileUtil;

import java.io.IOException;

import static ru.practice.kostin.shop.util.PhotoFileUtil.isImageExtension;
import static ru.practice.kostin.shop.util.PhotoFileUtil.isSizeAllowed;

@Service
public class ProductPhotoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPhotoService.class);

    @Value("${custom.image-directory-path}")
    private String fileDirectoryPath;

    public String saveFile(MultipartFile multipartFile) throws IOException, UnsupportedExtensionException {
        String fileExtension = FileUtil.getFileExtension(multipartFile.getOriginalFilename());
        if (isSizeAllowed(multipartFile.getSize()) && isImageExtension(fileExtension)) {
            return FileUtil.write(multipartFile, FileUtil.generateName("img_", String.valueOf(multipartFile.hashCode()),
                    fileExtension), fileDirectoryPath);
        }
        return null;
    }
}
