package com.mm.homebudgetassistant.register;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RegisterRepository extends JpaRepository<Register, String> {
   Optional<Register> findByName(String name);
}
