package ru.practice.kostin.shop.util;

import ru.practice.kostin.shop.exception.UnsupportedExtensionException;

public class PhotoFileUtil {

    private static final long ALLOWED_SIZE = 1048567;
    private static final String[] IMAGE_EXTENSIONS = {"png", "bmp", "jpg"};

    public static boolean isImageExtension(String fileExtension) throws UnsupportedExtensionException {
        for (String ext : IMAGE_EXTENSIONS) {
            if (fileExtension.equals(ext)) {
                return true;
            }
        }
        throw new UnsupportedExtensionException(String.format("%s is not supported or not image extension", fileExtension));
    }

    public static boolean isSizeAllowed(long size) {
        return size < ALLOWED_SIZE;
    }
}
