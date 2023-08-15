package com.tma.chatbox.service;

import com.tma.chatbox.entity.Rooms;
import com.tma.chatbox.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class RoomsServiceImpl implements RoomsService{

    @Autowired
    RoomsRepository roomsRepository;

    @Override
    public Rooms saveRoom(Rooms room) {
        return roomsRepository.save(room);
    }

    @Override
    public Rooms getRoomByUUID(UUID id) {
        Optional<Rooms> room = roomsRepository.findById(id);
        if(room.isPresent())
            return room.get();
        return null;
    }

    @Override
    public Set<Rooms> getRoomsByUUID(UUID id) {
        return Set.copyOf(roomsRepository.findAllById(Collections.singleton(id)));
    }
}
