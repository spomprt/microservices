package com.spomprt.orchestrator.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateCommand {

    @NotBlank
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;

}
