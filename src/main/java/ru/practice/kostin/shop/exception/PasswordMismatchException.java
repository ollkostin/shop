package ru.practice.kostin.shop.exception;

public class PasswordMismatchException extends Exception {

    public PasswordMismatchException(String message) {
        super(message);
    }

    public PasswordMismatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
