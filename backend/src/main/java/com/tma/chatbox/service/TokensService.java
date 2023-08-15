package com.tma.chatbox.service;

import com.tma.chatbox.entity.Messages;
import com.tma.chatbox.entity.Tokens;

import java.util.List;

public interface TokensService {

    void saveToken(Tokens token);

    List<Tokens> getAllTokens();



}
