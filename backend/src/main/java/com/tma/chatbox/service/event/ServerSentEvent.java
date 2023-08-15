package com.tma.chatbox.service.event;

import com.tma.chatbox.entity.Messages;
import com.tma.chatbox.entity.Users;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ServerSentEvent {
    private List<SseEmitter> emitterList = new CopyOnWriteArrayList<>();

    public SseEmitter stream(){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);
        emitterList.add(sseEmitter);
        sseEmitter.onCompletion(() -> emitterList.remove(sseEmitter));
        return sseEmitter;
    }

    public void sendMessageSSeEventToUI(Messages messages){
        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
        for(SseEmitter emitter : emitterList){
            try {
                emitter.send(SseEmitter.event().name(messages.getRoom().getId().toString()).data(messages));
            }catch (IOException e){
                emitter.complete();
                sseEmitterListToRemove.add(emitter);
            }
        }
        emitterList.removeAll(sseEmitterListToRemove);
    }

    public void sendUserSSeEventToUI(Users user){
        List<SseEmitter> sseEmitterListToRemove = new ArrayList<>();
        for(SseEmitter emitter : emitterList){
            try {
                emitter.send(SseEmitter.event().name("userPresence").data(user));
            }catch (IOException e){
                emitter.complete();
                sseEmitterListToRemove.add(emitter);
            }
        }
        emitterList.removeAll(sseEmitterListToRemove);
    }
}
