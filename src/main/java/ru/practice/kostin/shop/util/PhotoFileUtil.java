package ru.practice.kostin.shop.util;

import org.springframework.web.multipart.MultipartFile;
import ru.practice.kostin.shop.exception.FileTooLargeException;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;

public class PhotoFileUtil {

    private static final long ALLOWED_SIZE_BYTE = 10485760;
    private static final String[] IMAGE_EXTENSIONS = {"png", "bmp", "jpg"};

    /**
     * Checks if file extension is image extension
     * @param fileExtension file extension
     * @return <b>true</b> if file is image, <b>false</b> if not
     * @throws UnsupportedExtensionException file is not supported or not image extension
     */
    public static boolean isImageExtension(String fileExtension) throws UnsupportedExtensionException {
        for (String ext : IMAGE_EXTENSIONS) {
            if (fileExtension.equals(ext)) {
                return true;
            }
        }
        throw new UnsupportedExtensionException(String.format("%s is not supported or not image extension", fileExtension));
    }

    /**
     * Checks if file size is allowed
     * @param multipartFile file in in multipart/form-data format
     * @return <b>true</b> if file size is allowed
     * @throws FileTooLargeException if file size is not allowed
     */
    public static boolean isSizeAllowed(MultipartFile multipartFile) throws FileTooLargeException {
        if (multipartFile.getSize() < ALLOWED_SIZE_BYTE)
            return true;
        throw new FileTooLargeException(String.format("File with name \"%s\" is too large", multipartFile.getOriginalFilename()));
    }
}
