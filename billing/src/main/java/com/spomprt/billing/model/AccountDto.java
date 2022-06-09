package com.spomprt.billing.model;

import lombok.Data;

@Data
public class AccountDto {
    private String username;
    private String firstName;
    private String lastName;
    private Long bank;
}
