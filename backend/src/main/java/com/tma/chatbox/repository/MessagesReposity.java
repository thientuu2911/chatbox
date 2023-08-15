package com.tma.chatbox.repository;

import com.tma.chatbox.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessagesReposity extends JpaRepository<Messages, Integer> {
    List<Messages> findAll();
}
