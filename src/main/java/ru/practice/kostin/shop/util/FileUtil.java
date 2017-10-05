package ru.practice.kostin.shop.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

    public static String write(MultipartFile multipartFile, String name, String fileDirectoryPath) throws IOException {
        File file = new File(fileDirectoryPath, name);
        file.createNewFile();
        try (FileOutputStream stream = new FileOutputStream(file.getPath())) {
            stream.write(multipartFile.getBytes());
        }
        return file.getPath();
    }

    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1
                && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    public static String generateName(MultipartFile multipartFile, String prefix, String extension) {
        return String.format("%s_%s.%s", prefix, multipartFile.hashCode(), extension);
    }

}
