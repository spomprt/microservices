package com.spomprt.billing.handler;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spomprt.billing.model.CreateAccountDto;
import com.spomprt.billing.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsHandler {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    @KafkaListener(groupId = "billing", topics = {"outbox.event.user_created_event"})
    public void listenUserCreatedEvent(String value) {
        log.info(">> Handle user created event - {}", value);
        accountService.create(objectMapper.convertValue(value, CreateAccountDto.class));
    }

    @KafkaListener(groupId = "billing", topics = {"outbox.event.user_updated_event"})
    public void listenUserUpdatedEvent(String value) {
        log.info(">> Handle user updated event - {}", value);
    }

}
