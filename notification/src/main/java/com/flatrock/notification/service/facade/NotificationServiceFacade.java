package com.flatrock.notification.service.facade;


import com.flatrock.notification.model.Notification;
import com.flatrock.notification.model.NotificationRequest;
import com.flatrock.notification.service.MailService;
import com.flatrock.notification.service.NotificationService;
import com.flatrock.notification.service.SMSService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.flatrock.notification.entity.model.NotificationType;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationServiceFacade {
    public final NotificationService notificationService;
    public final SMSService smsService;
    public final MailService mailService;

    @Transactional
    public void sendNotifications(List<NotificationRequest> notificationList) {
        List<Notification> toSendSMS = new ArrayList<>();
        List<Notification> toSendMAIL = new ArrayList<>();

        for(NotificationRequest notificationRequest : notificationList){
            toSendSMS.add(new Notification(notificationRequest.getMessage(), NotificationType.SMS, notificationRequest.getRecipient()));
            toSendMAIL.add(new Notification(notificationRequest.getMessage(), NotificationType.EMAIL, notificationRequest.getRecipient()));
        }

        notificationService.create(toSendSMS);
        notificationService.create(toSendMAIL);

        smsService.send(toSendSMS);
        mailService.send(toSendMAIL);
    }

}
