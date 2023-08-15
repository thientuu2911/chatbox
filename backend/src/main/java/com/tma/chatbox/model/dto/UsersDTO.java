package com.tma.chatbox.model.dto;

import com.tma.chatbox.entity.Rooms;
import lombok.Data;

import java.util.Set;

@Data
public class UsersDTO {
    private int id;
    private String name;
    private boolean online;
    private Set<Rooms> rooms;
}
