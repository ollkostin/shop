package ru.practice.kostin.shop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.util.FileUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

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
    @Value("${shop.image.placeholder-name}")
    private String placeholderName;

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

    /**
     * Gets file from file system
     *
     * @param file         file
     * @param outputStream response output stream
     * @throws IOException
     */
    public void readFileToOutputStream(File file, OutputStream outputStream) throws IOException {
        FileUtil.readFileToOutputStream(file, outputStream);
    }

    /**
     * Sets content type, disposition and length into response headers
     *
     * @param file     file
     * @param response http response
     */
    public void setResponseContentImageHeaders(File file, HttpServletResponse response) {
        response.setHeader(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + ".jpg\"");
        response.setHeader(HttpHeaders.CONTENT_LENGTH, String.valueOf(file.length()));
    }

    public File getFileByProductIdAndFilename(Integer productId, String filename) {
        return new File(fileDirectoryPath + FILE_SEPARATOR + filePrefix + productId + FILE_SEPARATOR + filename);
    }

    public File getPlaceholderImage() {
        return new File(fileDirectoryPath + FILE_SEPARATOR + placeholderName);
    }
}
