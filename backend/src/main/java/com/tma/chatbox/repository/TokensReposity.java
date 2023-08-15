package com.tma.chatbox.repository;

import com.tma.chatbox.entity.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokensReposity extends JpaRepository<Tokens, Integer> {
    List<Tokens> findAll();
}
