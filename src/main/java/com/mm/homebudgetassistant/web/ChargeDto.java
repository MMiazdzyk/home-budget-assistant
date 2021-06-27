package com.mm.homebudgetassistant.web;

import java.math.BigDecimal;

class ChargeDto {
    private String registerName;
    private BigDecimal value;

    public ChargeDto(){}

    public ChargeDto(String name, BigDecimal value) {
        this.registerName = name;
        this.value = value;
    }

    public String getRegisterName() {
        return registerName;
    }

    public BigDecimal getValue() {
        return value;
    }
}
