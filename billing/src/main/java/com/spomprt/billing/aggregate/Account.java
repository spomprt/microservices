package com.spomprt.billing.aggregate;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    @Id
    private String username;

    private String firstName;

    private String lastName;

    @Column(name = "bank")
    private long bank;

    public void putToBank(Long money) {
        this.bank += money;
    }

    public void withdraw(Long money) {
        if ((this.bank - money) < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        this.bank -= money;
    }

}
