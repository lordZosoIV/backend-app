package com.flatrcok.order.service.external.queue.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    private String message;

    private String type;

    private String recipient;

}