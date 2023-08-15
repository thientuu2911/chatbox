package com.tma.chatbox.service;

import com.tma.chatbox.entity.Messages;

import java.util.List;

public interface MessagesService {

    void saveMessage(Messages messages);

    List<Messages> getAllMessages();



}
