package com.flatrock.notification.model;

import com.flatrock.notification.entity.model.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    private String message;

    private NotificationType type;

    private String recipient;

}