package com.GraduationProject.demo.service;

import com.GraduationProject.demo.model.NotificationModel;
import com.google.firebase.messaging.*;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendToToken(NotificationModel request) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().send(
                buildMessage(request, true)
        );
    }

    public String sendToTopic(NotificationModel request) throws FirebaseMessagingException {
        return FirebaseMessaging.getInstance().send(
                buildMessage(request, false)
        );
    }

    public String sendToAll(NotificationModel request) throws FirebaseMessagingException {
        request.setTopic("all");
        return FirebaseMessaging.getInstance().send(
                buildMessage(request, false)
        );
    }

    private Message buildMessage(NotificationModel request, boolean toToken) {
        Notification notification = Notification.builder()
                .setTitle(request.getTitle())
                .setBody(request.getBody())
                .setImage(request.getImageUrl())
                .build();

        AndroidNotification androidNotification = AndroidNotification.builder()
                .setClickAction(request.getClickAction())
                .build();

        AndroidConfig androidConfig = AndroidConfig.builder()
                .setNotification(androidNotification)
                .build();

        Message.Builder messageBuilder = Message.builder()
                .setNotification(notification)
                .setAndroidConfig(androidConfig);

        if (toToken) {
            messageBuilder.setToken(request.getToken());
        } else {
            messageBuilder.setTopic(request.getTopic());
        }

        return messageBuilder.build();
    }
}