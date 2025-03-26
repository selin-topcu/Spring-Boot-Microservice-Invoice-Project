package com.user.service.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserException extends RuntimeException{
    private HttpStatus httpStatus;
    private String errorMessage;
    private List<String> errorDetails;

    public UserException(String errorMessage, List<String> errors, HttpStatus httpStatus) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorDetails = errors;
        this.httpStatus = httpStatus;
    }
}
