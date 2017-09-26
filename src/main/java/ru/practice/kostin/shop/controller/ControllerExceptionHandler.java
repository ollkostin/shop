package ru.practice.kostin.shop.controller;

import javassist.NotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import ru.practice.kostin.shop.service.dto.error.ErrorDTO;


@ControllerAdvice
@RestController
public class ControllerExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ErrorDTO notFoundException(NotFoundException ex) {
        return new ErrorDTO("404", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ErrorDTO internalServerException(Exception ex) {
        return new ErrorDTO("500", ex.getMessage());
    }
}
