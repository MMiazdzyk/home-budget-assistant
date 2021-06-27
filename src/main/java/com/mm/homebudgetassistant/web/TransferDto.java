package com.mm.homebudgetassistant.web;

import java.math.BigDecimal;

public class TransferDto {
    private String fromRegisterName;
    private String toRegisterName;
    private BigDecimal value;

    public TransferDto() {}

    public TransferDto(String fromRegisterName, String toRegisterName, BigDecimal value) {
        this.fromRegisterName = fromRegisterName;
        this.toRegisterName = toRegisterName;
        this.value = value;
    }

    public String getFromRegisterName() {
        return fromRegisterName;
    }

    public String getToRegisterName() {
        return toRegisterName;
    }

    public BigDecimal getValue() {
        return value;
    }
}
