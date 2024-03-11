package com.q100s.microtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFountException extends RuntimeException {
    public DataNotFountException() {
    }
}