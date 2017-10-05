package ru.practice.kostin.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;

public class FileUtil {

    private static final int BUFFER_SIZE = 16384;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    public static String write(MultipartFile multipartFile, String name, String fileDirectoryPath) throws IOException {
        LOGGER.info(String.format("Writing file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
        File file = new File(fileDirectoryPath, name);
        boolean created = file.createNewFile();
        if (created) {
            try (FileOutputStream stream = new FileOutputStream(file.getPath())) {
                byte[] buffer = new byte[BUFFER_SIZE];
                InputStream str = multipartFile.getInputStream();
                while (str.read(buffer) != -1) {
                    stream.write(buffer);
                }
            }
            LOGGER.info(String.format("Successfully saved file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
            return file.getPath();
        } else {
            String errorMessage = String.format("File with name \"%s\" already exists in directory \"%s\"", name, fileDirectoryPath);
            LOGGER.error(errorMessage);
            throw new FileAlreadyExistsException(errorMessage);
        }
    }

    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1
                && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static String generateName(String prefix, String body, String extension) {
        return String.format("%s_%s.%s", prefix, body, extension);
    }

}
