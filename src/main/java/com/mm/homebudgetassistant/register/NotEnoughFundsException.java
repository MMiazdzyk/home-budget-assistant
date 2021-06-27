package com.mm.homebudgetassistant.register;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Not enough founds")
public class NotEnoughFundsException extends RuntimeException {

}
