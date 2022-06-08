package com.spomprt.user.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateDto {
    @NotBlank
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
