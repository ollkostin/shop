package ru.practice.kostin.shop.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;

public class FileUtil {

    public static final String FILE_SEPARATOR = "/";
    private static final int BUFFER_SIZE = 16384;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * Writes file into directory
     *
     * @param multipartFile     file in in multipart/form-data format
     * @param name              file name
     * @param fileDirectoryPath path to directory
     * @return path to created file
     * @throws IOException
     */
    public static String write(MultipartFile multipartFile, String name, String fileDirectoryPath) throws IOException {
        LOGGER.info(String.format("Writing file with name \"%s\" in directory \"%s\" ", name, fileDirectoryPath));
        File directory = new File(fileDirectoryPath);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                throw new IOException("cannot not create file");
            }
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
        }
        String errorMessage = String.format("File with name \"%s\" already exists in directory \"%s\"", name, fileDirectoryPath);
        LOGGER.error(errorMessage);
        throw new FileAlreadyExistsException(errorMessage);
    }

    /**
     * Gets extension of file from name
     *
     * @param fileName file name
     * @return extension
     */
    public static String getFileExtension(String fileName) {
        if (fileName.lastIndexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }

    /**
     * Reads file into output stream
     *
     * @param file     file
     * @param response http response
     * @return file in bytes
     * @throws IOException
     */
    public static void readFileIntoResponse(File file, HttpServletResponse response) throws IOException {
        try (FileInputStream is = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(is);
             OutputStream os = response.getOutputStream()) {
            int b;
            while ((b = bis.read()) != -1) {
                os.write(b);
            }
        }
    }
}
