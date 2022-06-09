package com.spomprt.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommandsHandler {

    private final ObjectMapper objectMapper;

    @KafkaListener(groupId = "user", topics = {"user-create-command"})
    public void listen(String value) {
        System.out.println(value);
    }
}
