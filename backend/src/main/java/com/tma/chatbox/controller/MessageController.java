package com.tma.chatbox.controller;

import com.tma.chatbox.entity.Messages;
import com.tma.chatbox.entity.NotificationMessage;
import com.tma.chatbox.service.FirebaseMessagingService;
import com.tma.chatbox.service.MessagesService;
import com.tma.chatbox.service.event.ServerSentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/chatbox/message")
public class MessageController {

    @Autowired
    MessagesService messagesService;

    @Autowired
    ServerSentEvent serverSentEvent;

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @GetMapping(value = "/stream")
    public SseEmitter stream() {
        return serverSentEvent.stream();
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Messages>> listMessages(){
         return ResponseEntity.ok(messagesService.getAllMessages());
    }

    @PostMapping(value = "/send")
    public void sendMessage(@RequestBody Messages messages){
        messagesService.saveMessage(messages);
        serverSentEvent.sendMessageSSeEventToUI(messages);
        NotificationMessage notificationMessage = new NotificationMessage();
        notificationMessage.setBody(messages.getText());
        notificationMessage.setTitle(messages.getSender().getName());
        notificationMessage.setRecipientToken("fKMIdG0-wVPjDTJuCOCAZi:APA91bHQxeYPk8r4o8a8jhs9qhhmByJlElo8NBno8HWGdglZVSUod_AOTDSvg7PjCC9eExfbGv7BS0j-9h1qNF8xWUgc7AOB7lpphJ5vpjcCnPjIv5ey6HPKZ1BxAXWjCwD35GFPsJRk");
        firebaseMessagingService.sendNotification(notificationMessage);
    }
}
