package com.tma.chatbox.service;

import com.tma.chatbox.entity.Messages;
import com.tma.chatbox.repository.MessagesReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    MessagesReposity messagesReposity;

    @Override
    public void saveMessage(Messages messages) {
        messagesReposity.save(messages);
    }

    @Override
    public List<Messages> getAllMessages() {
        return messagesReposity.findAll();
    }
}
