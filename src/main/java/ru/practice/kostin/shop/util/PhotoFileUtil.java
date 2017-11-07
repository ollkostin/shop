package ru.practice.kostin.shop.util;

import org.springframework.web.multipart.MultipartFile;

public class PhotoFileUtil {

    public static final long ALLOWED_SIZE_BYTE = 10485760;
    public static final String[] IMAGE_EXTENSIONS = {"png", "jpg"};

    /**
     * Checks if file extension is image extension
     *
     * @param file file in in multipart/form-data format
     * @return <b>true</b> if file is image, <b>false</b> if not
     */
    public static boolean fileHasImageExtension(MultipartFile file) {
        String fileExtension = FileUtil.getFileExtension(file.getOriginalFilename());
        for (String ext : IMAGE_EXTENSIONS) {
            if (fileExtension.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if file size is allowed
     *
     * @param multipartFile file in in multipart/form-data format
     * @return <b>true</b> if file size is allowed
     */
    public static boolean isSizeAllowed(MultipartFile multipartFile) {
        return multipartFile.getSize() < ALLOWED_SIZE_BYTE;
    }
}
