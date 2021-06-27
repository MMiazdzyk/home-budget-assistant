package com.mm.homebudgetassistant.register;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Register not found")
public class RegisterNotFoundException extends RuntimeException {

}
