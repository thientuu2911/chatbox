package com.tma.chatbox.controller;

import com.tma.chatbox.entity.Rooms;
import com.tma.chatbox.service.RoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/chatbox/room")
public class RoomController {

    @Autowired
    RoomsService roomsService;

    @GetMapping(value = "/create")
    public Rooms createRoom(){
        return roomsService.saveRoom(new Rooms());
    }

    @GetMapping(value = "/{id}")
    public Rooms getRoom(@PathVariable("id") UUID id){
        return roomsService.getRoomByUUID(id);
    }

}
