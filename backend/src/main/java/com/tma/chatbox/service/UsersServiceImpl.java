package com.tma.chatbox.service;

import com.tma.chatbox.entity.Users;
import com.tma.chatbox.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository userRepository;

    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users getUserById(int id) {
        Optional<Users> user = userRepository.findById(id);
        if(user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public Users getUserByName(String name) {
        Optional<Users> user = userRepository.findByName(name);
        if(user.isPresent())
            return user.get();
        return null;
    }

    @Override
    public List<Users> getAllUsers(){
        return userRepository.findAll().stream().filter(u -> u.isOnline()).collect(Collectors.toList());
    }
}
