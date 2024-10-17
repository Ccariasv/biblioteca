package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}