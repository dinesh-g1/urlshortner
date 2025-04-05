package com.dinesh.urlshortner.web.advices;

import com.dinesh.urlshortner.domain.exceptions.ShortURLNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(ShortURLNotFoundException.class)
    public String handleShortURLNotFoundException(ShortURLNotFoundException e) {
        return "error/404.html";
    }
}
