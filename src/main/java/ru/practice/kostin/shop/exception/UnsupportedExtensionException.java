package ru.practice.kostin.shop.exception;

public class UnsupportedExtensionException extends Exception {

    public UnsupportedExtensionException(String message) {
        super(message);
    }

    public UnsupportedExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
