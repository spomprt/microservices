package com.spomprt.billing.model;

import lombok.Data;

@Data
public class CreateAccountDto {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
}
