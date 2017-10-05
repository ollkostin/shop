package ru.practice.kostin.shop.util;

import ru.practice.kostin.shop.exception.UnsupportedExtensionException;

public class PhotoFileUtil {

    private static final String[] imageExtensions = {"png", "bmp", "jpg"};

    public static boolean isImageExtension(String fileExtension) throws UnsupportedExtensionException {
        for (String ext : imageExtensions) {
            if (fileExtension.equals(ext)) {
                return true;
            }
        }
        throw new UnsupportedExtensionException("file extension is not supported");
    }
}
