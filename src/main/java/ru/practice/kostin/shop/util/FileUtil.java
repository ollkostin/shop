package ru.practice.kostin.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class FileUtil {

    private static final int BUFFER_SIZE = 16384;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * Writes file into directory
     * @param multipartFile file in in multipart/form-data format
     * @param name file name
     * @param fileDirectoryPath path to directory
     * @return path to created file
     * @throws IOException
     */
    public static String write(MultipartFile multipartFile, String name, String fileDirectoryPath) throws IOException {
        LOGGER.info(String.format("Writing file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
        File directory = new File(fileDirectoryPath);
        if (!directory.exists()){
            directory.mkdir();
        }
        File file = new File(directory, name);
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

    /**
     * Gets extension of file from name
     * @param fileName file name
     * @return extension
     */
    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") != -1
                && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    /**
     *  Creates filename from prefix, body and file extension
     * @param prefix prefix
     * @param body body
     * @param extension extension
     * @return full filename
     */
    public static String generateName(String prefix, String body) {
        return String.format("%s%s", prefix, body);
    }

    /**
     * Reads file by path
     * @param pathToFile path to file
     * @return file in bytes
     * @throws IOException
     */
    public static byte[] getFile(String pathToFile) throws IOException {
        File file = new File(pathToFile);
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file)) {
                byte[] bytes = new byte[inputStream.available()];
                inputStream.read(bytes);
                return bytes;
            }
        }
        return null;
    }


}
