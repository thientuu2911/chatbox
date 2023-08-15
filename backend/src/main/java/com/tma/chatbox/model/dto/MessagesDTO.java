package com.tma.chatbox.model.dto;

import com.tma.chatbox.entity.Rooms;
import com.tma.chatbox.entity.Users;
import lombok.Data;

@Data
public class MessagesDTO {
    private int id;
    private Users sender;
    private Rooms room;
    private String text;
}
