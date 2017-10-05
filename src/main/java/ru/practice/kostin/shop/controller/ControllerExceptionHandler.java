package ru.practice.kostin.shop.controller;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.exception.UnsupportedExtensionException;
import ru.practice.kostin.shop.service.dto.error.ErrorDTO;


@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO notFoundException(NotFoundException ex) {
        return new ErrorDTO("404", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO internalServerException(Exception ex) {
        return new ErrorDTO("500", ex.getMessage());
    }

    @ExceptionHandler(UnsupportedExtensionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO unsupportedExtensionException(UnsupportedExtensionException ex) {
        return new ErrorDTO("400", ex.getMessage());
    }
}
