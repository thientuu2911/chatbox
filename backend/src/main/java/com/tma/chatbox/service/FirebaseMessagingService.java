package com.tma.chatbox.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.tma.chatbox.entity.NotificationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class FirebaseMessagingService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public void subscribeToTopic(String token, String topic) throws FirebaseMessagingException {
        FirebaseMessaging.getInstance().subscribeToTopic(new ArrayList<>(Collections.singleton(token)), topic);
    }

    public String sendNotification(NotificationMessage notificationMessage){
        Notification notification = Notification.builder()
                .setTitle(notificationMessage.getTitle())
                .setBody(notificationMessage.getBody())
                .setImage(notificationMessage.getImage())
                .build();
        Message message = Message.builder()
                    .setToken(notificationMessage.getRecipientToken())
                    .setNotification(notification)
                    .putAllData(notificationMessage.getData())
                    .build();
        System.out.println(message.toString());
        try {
            firebaseMessaging.send(message);
            return "Success Sending Notification";
        }catch (FirebaseMessagingException e){
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
