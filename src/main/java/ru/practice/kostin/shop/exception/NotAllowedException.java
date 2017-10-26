package ru.practice.kostin.shop.exception;

public class NotAllowedException extends Exception {

    public NotAllowedException(String message) {
        super(message);
    }

    public NotAllowedException(String message, Throwable cause) {
        super(message, cause);
    }
}
