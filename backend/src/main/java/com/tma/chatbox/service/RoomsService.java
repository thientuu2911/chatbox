package com.tma.chatbox.service;

import com.tma.chatbox.entity.Rooms;

import java.util.Set;
import java.util.UUID;

public interface RoomsService {
    Rooms saveRoom(Rooms room);

    Rooms getRoomByUUID(UUID id);
    Set<Rooms> getRoomsByUUID(UUID id);
}
