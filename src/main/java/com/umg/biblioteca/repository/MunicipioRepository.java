package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {
}