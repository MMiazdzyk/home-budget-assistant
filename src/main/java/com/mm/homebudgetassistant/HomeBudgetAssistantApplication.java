package com.mm.homebudgetassistant;

import com.mm.homebudgetassistant.register.Register;
import com.mm.homebudgetassistant.register.RegisterRepository;
import com.mm.homebudgetassistant.register.RegisterOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class HomeBudgetAssistantApplication {

    private static final Logger log = LoggerFactory.getLogger(HomeBudgetAssistantApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HomeBudgetAssistantApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RegisterRepository RegisterRepository, RegisterOperation RegisterOperation) {
        if (!RegisterRepository.findAll().isEmpty()) {
            return (args) -> {
                RegisterOperation.getAllRegisters().stream().forEach(System.out::println);
            };
        } else
            return (args) -> {
                Register wallet = new Register("Wallet", BigDecimal.valueOf(1000));
                RegisterRepository.save(wallet);
                log.info("Wallet register was created: " + wallet.toString());

                Register savings = new Register("Savings", BigDecimal.valueOf(5000));
                RegisterRepository.save(savings);
                log.info("Savings register was created: " + savings.toString());

                Register insurancePolicy = new Register("Insurance_policy", BigDecimal.valueOf(0));
                RegisterRepository.save(insurancePolicy);
                log.info("Insurance policy register was created: " + insurancePolicy.toString());

                Register foodExpenses = new Register("Food_expenses", BigDecimal.valueOf(0));
                RegisterRepository.save(foodExpenses);
                log.info("Insurance policy register was created: " + foodExpenses.toString());

                RegisterOperation.charge("Wallet", BigDecimal.valueOf(2500));
                RegisterOperation.transfer("Wallet", "Food_expenses", BigDecimal.valueOf(1500));
                RegisterOperation.transfer("Savings", "Insurance_policy", BigDecimal.valueOf(500));
                RegisterOperation.transfer("Wallet", "Savings", BigDecimal.valueOf(1000));

                RegisterOperation.getAllRegisters().stream().forEach(System.out::println);
            };
    }

}
