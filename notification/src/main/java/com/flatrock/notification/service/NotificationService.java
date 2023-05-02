package com.flatrock.notification.service;

import com.flatrock.notification.entity.NotificationEntity;
import com.flatrock.notification.model.Notification;
import com.flatrock.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository repository;

    public void create(List<Notification> notificationDtoList) {
        List<NotificationEntity> notificationEntities = new ArrayList<>();
        for (Notification notificationDto : notificationDtoList) {
            NotificationEntity entity = new NotificationEntity();
            entity.setMessage(notificationDto.getMessage());
            entity.setType(notificationDto.getType());
            entity.setRecipient(notificationDto.getRecipient());
            notificationEntities.add(entity);
        }

        repository.saveAll(notificationEntities);
    }

}
