package com.flatrcok.order.exception.detail;

import com.flatrcok.order.util.TimestampUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class HandledExceptionDetail {
    private String message;
    private Timestamp timestamp;

    public HandledExceptionDetail(String message) {
        this(message, TimestampUtils.now());
    }

    private HandledExceptionDetail(String message, Timestamp timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
