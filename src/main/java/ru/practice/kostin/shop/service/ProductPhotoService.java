package ru.practice.kostin.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.util.FileUtil;

import java.io.IOException;

@Service
public class ProductPhotoService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${custom.image-directory-path}")
    private String fileDirectoryPath;

    public String saveFile(MultipartFile multipartFile) throws IOException {//, UnsupportedExtensionException {
        String path;
        String name = multipartFile.getOriginalFilename();
        try {
            log.info(String.format("Writing file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
            path = FileUtil.write(multipartFile, name, fileDirectoryPath);
            log.info(String.format("Successfully saved file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
        } catch (IOException e) {
            log.error(String.format("Should have save file with name \"%s\" in directory \"%s\" ," +
                    " but got exception: %s", name, fileDirectoryPath, e.getMessage()));
            throw e;
        }
        return path;
    }
}
