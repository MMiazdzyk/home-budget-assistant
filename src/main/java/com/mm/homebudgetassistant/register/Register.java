package com.mm.homebudgetassistant.register;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Register {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal balance;

    public Register() {
    }

    public Register(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    void charge(BigDecimal value) {
        balance = balance.add(value);
    }

    void withdraw(BigDecimal value) {
        if (hasFunds(value)) {
            balance = balance.subtract(value);
        } else {
            throw new NotEnoughFundsException();
        }
    }

    private boolean hasFunds(BigDecimal value) {
        return balance.subtract(value).compareTo(new BigDecimal(0)) >= 0;
    }

    @Override
    public String toString() {
        return name.replace("_", " ") + ": " + balance;
    }
}
