package com.tma.chatbox.service;

import com.tma.chatbox.entity.Users;

import java.util.List;

public interface UsersService {
    Users saveUser(Users user);

    Users getUserById(int id);

    Users getUserByName(String name);

    List<Users> getAllUsers();
}
