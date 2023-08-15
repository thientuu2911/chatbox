package com.tma.chatbox.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.tma.chatbox.entity.Tokens;
import com.tma.chatbox.entity.Users;
import com.tma.chatbox.model.request.LoginRequest;
import com.tma.chatbox.service.FirebaseMessagingService;
import com.tma.chatbox.service.RoomsService;
import com.tma.chatbox.service.TokensService;
import com.tma.chatbox.service.UsersService;
import com.tma.chatbox.service.event.ServerSentEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/chatbox/user")
public class UserController {
    @Autowired
    TokensService tokensService;

    @Autowired
    UsersService usersService;

    @Autowired
    RoomsService roomsService;

    @Autowired
    ServerSentEvent serverSentEvent;

    @Autowired
    FirebaseMessagingService firebaseMessagingService;

    @PostMapping(value = "/login")
    public Users login(@RequestBody LoginRequest loginRequest) throws FirebaseMessagingException {
        Users user = usersService.getUserByName(loginRequest.getName());
        if(user == null){
            user = new Users();
            user.setName(loginRequest.getName());
            user.setRooms(roomsService.getRoomsByUUID(UUID.fromString("7dfe5f50-806c-47cd-aaa6-1019d96a28ab")));
        }
        user.setOnline(true);
        user = usersService.saveUser(user);
        if(loginRequest.getToken() != null) {
            Users finalUser = user;
            System.out.println(!user.getTokens().stream().filter(t -> {
                return t.getToken().equalsIgnoreCase(loginRequest.getToken())
                        && t.getUser().getName().equalsIgnoreCase(finalUser.getName());
            }).findAny().isEmpty());
            if (user.getTokens().isEmpty() || user.getTokens().stream().filter(t -> t.getToken().equalsIgnoreCase(loginRequest.getToken())
                    && t.getUser().getName().equalsIgnoreCase(finalUser.getName())).findAny().isEmpty()) {
                tokensService.saveToken(new Tokens(loginRequest.getToken(), user));
                firebaseMessagingService.subscribeToTopic(loginRequest.getToken(), "test");
            }
        }
        serverSentEvent.sendUserSSeEventToUI(user);
        return user;
    }

    @PostMapping(value = "/logout")
    public void logout(@RequestBody LoginRequest loginRequest){
        Users user = usersService.getUserByName(loginRequest.getName());
        user.setOnline(false);
        user = usersService.saveUser(user);
        serverSentEvent.sendUserSSeEventToUI(user);
    }

    @GetMapping(value = "/getAll")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }
}
