package com.mm.homebudgetassistant.web;

import com.mm.homebudgetassistant.register.Register;
import com.mm.homebudgetassistant.register.RegisterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.stream.Collectors;

@RestController
public class RegisterController {
    private final RegisterOperation RegisterOperation;

    public RegisterController(RegisterOperation RegisterOperation) {
        this.RegisterOperation = RegisterOperation;
    }

    @GetMapping(path = "/balance")
    public String balance() {
        return RegisterOperation.balance().stream().map(Register::toString).collect(Collectors.joining("\n"));
    }

    @PutMapping(path = "/charge",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> charge(@RequestBody ChargeDto chargeRequest) {
        RegisterOperation.charge(chargeRequest.getRegisterName(), chargeRequest.getValue());
        return new ResponseEntity("Register \"" + chargeRequest.getRegisterName() + "\" was charged " + chargeRequest.getValue(), HttpStatus.OK);
    }

    @PutMapping(path = "/transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> transfer(@RequestBody TransferDto transferDto) {
        RegisterOperation.transfer(transferDto.getFromRegisterName(), transferDto.getToRegisterName(), transferDto.getValue());
        return new ResponseEntity(transferDto.getValue() + " was transferred from Register \""
                + transferDto.getFromRegisterName() + "\" to register \"" + transferDto.getToRegisterName() + "\"",
                HttpStatus.OK);
    }
}
