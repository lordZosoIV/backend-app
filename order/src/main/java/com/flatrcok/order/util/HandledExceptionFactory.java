package com.flatrcok.order.util;


import com.flatrcok.order.exception.HandledException;
import com.flatrcok.order.exception.detail.HandledExceptionDetail;
import org.springframework.http.HttpStatus;

public final class HandledExceptionFactory {

    public static HandledException getHandledException(String message) {
        return getHandledException(HttpStatus.BAD_REQUEST, message);
    }

    public static HandledException getHandledException(HttpStatus status, String message) {
        return new HandledException(status, new HandledExceptionDetail(message));
    }
}
