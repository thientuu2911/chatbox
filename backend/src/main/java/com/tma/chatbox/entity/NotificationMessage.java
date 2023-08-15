package com.tma.chatbox.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
public class NotificationMessage {
    private String recipientToken;
    private String title;
    private String body;
    private String image;
    private String topic;
    private Map<String,String> data = new HashMap<>();
}
