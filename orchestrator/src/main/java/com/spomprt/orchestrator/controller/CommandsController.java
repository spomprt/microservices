package com.spomprt.orchestrator.controller;

import com.spomprt.orchestrator.command.UserCreateCommand;
import com.spomprt.orchestrator.service.CommandsHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("command")
@RequiredArgsConstructor
public class CommandsController {

    private final CommandsHandler commandsHandler;

    @PostMapping
    public void handleCommand(@Valid @RequestBody UserCreateCommand userCreateCommand) {
        log.info(">> Handle user create command");
        commandsHandler.handleCommand(userCreateCommand);
    }


}
