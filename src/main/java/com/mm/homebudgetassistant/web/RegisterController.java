package com.mm.homebudgetassistant.web;

import com.mm.homebudgetassistant.register.Register;
import com.mm.homebudgetassistant.register.RegisterOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegisterController {
    private final RegisterOperation registerOperation;

    public RegisterController(RegisterOperation RegisterOperation) {
        this.registerOperation = RegisterOperation;
    }

    @GetMapping(path = "/registers",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Register> balance() {
        return registerOperation.getAllRegisters();
    }

    @PutMapping(path = "/registers/{id}/charge",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public void chargeRegister(@RequestBody ChargeDto chargeRequest, @PathVariable Long id) {
        registerOperation.charge(id, chargeRequest.getValue());
    }

    @PostMapping(path = "/transfer",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void  transfer(@RequestBody TransferDto transferDto) {
        registerOperation.transfer(transferDto.getFromRegisterName(), transferDto.getToRegisterName(), transferDto.getValue());
    }
}
