package com.product.service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class ExceptionBusiness extends RuntimeException{

    private final HttpStatus status;

    public ExceptionBusiness(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
