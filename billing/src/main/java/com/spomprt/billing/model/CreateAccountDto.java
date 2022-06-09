package com.spomprt.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountDto {
    private String username;
    private String firstName;
    private String lastName;
}
