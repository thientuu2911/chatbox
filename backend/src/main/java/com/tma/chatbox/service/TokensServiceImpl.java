package com.tma.chatbox.service;

import com.tma.chatbox.entity.Messages;
import com.tma.chatbox.entity.Tokens;
import com.tma.chatbox.repository.MessagesReposity;
import com.tma.chatbox.repository.TokensReposity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokensServiceImpl implements TokensService {

    @Autowired
    TokensReposity tokensReposity;

    @Override
    public void saveToken(Tokens token) {
        tokensReposity.save(token);
    }

    @Override
    public List<Tokens> getAllTokens() {
        return tokensReposity.findAll();
    }
}
