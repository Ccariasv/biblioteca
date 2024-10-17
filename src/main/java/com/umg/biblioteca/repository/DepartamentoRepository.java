package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}