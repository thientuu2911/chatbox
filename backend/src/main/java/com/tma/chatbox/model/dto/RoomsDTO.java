package com.tma.chatbox.model.dto;

import com.tma.chatbox.entity.Users;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class RoomsDTO {
    private UUID id;
    private String name;
    private Set<Users> users;
}
