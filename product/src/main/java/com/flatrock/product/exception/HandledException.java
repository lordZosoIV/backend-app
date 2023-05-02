package com.flatrock.product.exception;

import com.flatrock.product.exception.detail.HandledExceptionDetail;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HandledException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final HandledExceptionDetail detail;

    public HandledException(HttpStatus status, HandledExceptionDetail detail) {
        super(detail.getMessage());
        this.httpStatus = status;
        this.detail = detail;
    }
}
