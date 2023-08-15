package com.tma.chatbox.model.request;

import lombok.Data;

@Data
public class LoginRequest {
    private int id;
    private String name;
    private String token;
}
