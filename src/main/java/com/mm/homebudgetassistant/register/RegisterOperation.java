package com.mm.homebudgetassistant.register;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RegisterOperation {
    private final RegisterRepository RegisterRepository;

    public RegisterOperation(RegisterRepository RegisterRepository) {
        this.RegisterRepository = RegisterRepository;
    }

    @Transactional
    public void charge(String name, BigDecimal value) {
        Register Register = RegisterRepository.findByName(name).orElseThrow(RegisterNotFoundException::new);
        Register.charge(value);
    }

    @Transactional
    public void transfer(String from, String to, BigDecimal value) {
        Register fromRegister = RegisterRepository.findByName(from).orElseThrow(RegisterNotFoundException::new);
        Register toRegister = RegisterRepository.findByName(to).orElseThrow(RegisterNotFoundException::new);
        fromRegister.withdraw(value);
        toRegister.charge(value);
    }

    public List<Register> getAllRegisters() {
        return RegisterRepository.findAll();
    }

}
