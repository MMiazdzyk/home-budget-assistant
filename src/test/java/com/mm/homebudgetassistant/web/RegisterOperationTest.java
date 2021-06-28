package com.mm.homebudgetassistant.web;

import com.mm.homebudgetassistant.register.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RegisterOperationTest {
    @Autowired
    private RegisterOperation registerOperation;
    @Autowired
    private RegisterRepository registerRepository;

    @BeforeEach
    public void init(){
        registerRepository.deleteAll();
    }

    @Test
    @DisplayName("Should charge register with value")
    void shouldChargeRegisterWithValue() {
        Register register = new Register("register", new BigDecimal(0));
        registerRepository.save(register);

        registerOperation.charge(register.getId(), new BigDecimal(100));

        assertEquals(new BigDecimal(100).intValue(),
                registerRepository.findByName("register").orElseThrow(RegisterNotFoundException::new).getBalance().intValue());
    }

    @Test
    @DisplayName("Should transfer between registers")
    void shouldTransferBetweenRegisters() {
        Register wallet = new Register("wallet", new BigDecimal(100));
        Register savings = new Register("savings", new BigDecimal(100));
        registerRepository.saveAll(Arrays.asList(wallet,savings));

        registerOperation.transfer("wallet","savings",new BigDecimal(100));

        assertEquals(new BigDecimal(0).intValue(),
                registerRepository.findByName("wallet").orElseThrow(RegisterNotFoundException::new).getBalance().intValue());
        assertEquals(new BigDecimal(200).intValue(),
                registerRepository.findByName("savings").orElseThrow(RegisterNotFoundException::new).getBalance().intValue());
    }

    @Test
    @DisplayName("Should throw exception when no enough founds")
    void shouldThrowExceptionWhenNoEnoughFounds() {
        Register wallet = new Register("wallet", new BigDecimal(50));
        Register savings = new Register("savings", new BigDecimal(100));
        registerRepository.saveAll(Arrays.asList(wallet,savings));

        Assertions.assertThrows(NotEnoughFundsException.class, ()-> {
            registerOperation.transfer("wallet","savings", new BigDecimal(100));
        });
    }
}
