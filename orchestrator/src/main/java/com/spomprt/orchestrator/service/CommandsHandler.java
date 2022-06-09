package com.spomprt.orchestrator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spomprt.orchestrator.aggregate.Command;
import com.spomprt.orchestrator.command.UserCreateCommand;
import com.spomprt.orchestrator.repository.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandsHandler {

    private final ObjectMapper objectMapper;
    private final CommandRepository commandRepository;

    public void handleCommand(UserCreateCommand userCreateCommand) {
        JsonNode userCreatePayload = objectMapper.valueToTree(userCreateCommand);

        Command command = Command.builder()
                .aggregateid(userCreateCommand.getUsername())
                .aggregatetype("USER")
                .type("CREATE")
                .payload(userCreatePayload)
                .build();

        commandRepository.save(command);
    }

}
