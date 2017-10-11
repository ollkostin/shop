package ru.practice.kostin.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.util.FileUtil;

import java.io.IOException;

import static ru.practice.kostin.shop.util.FileUtil.FILE_SEPARATOR;
import static ru.practice.kostin.shop.util.PhotoFileUtil.isImageExtension;
import static ru.practice.kostin.shop.util.PhotoFileUtil.isSizeAllowed;

@Service
public class FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileService.class);

    @Value("${shop.image.directory-path}")
    private String fileDirectoryPath;
    @Value("${shop.image.prefix}")
    private String filePrefix;

    /**
     * Saves image
     *
     * @param multipartFile file in in multipart/form-data format
     * @param productId     product id
     * @return path to file
     * @throws IOException
     * @throws UnsupportedExtensionException not an image or extension is not supported
     * @throws FileTooLargeException         size of file is not supported
     */
    public String saveFile(MultipartFile multipartFile, Integer productId) throws IOException, UnsupportedExtensionException, FileTooLargeException {
        String fileExtension = FileUtil.getFileExtension(multipartFile.getOriginalFilename());
        if (isSizeAllowed(multipartFile) && isImageExtension(fileExtension)) {
            return FileUtil.write(
                    multipartFile,
                    filePrefix + productId + "_" + multipartFile.hashCode(),
                    fileDirectoryPath + FILE_SEPARATOR + filePrefix + productId
            );
        }
        return null;
    }

    public byte[] getFile(Integer id, String filename) throws IOException, FileTooLargeException {
        return FileUtil.getFile(fileDirectoryPath + FILE_SEPARATOR + filePrefix + id + FILE_SEPARATOR + filename);
    }
}
