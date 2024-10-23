package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Municipio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MunicipioRepository extends JpaRepository<Municipio, Long> {

    List<Municipio> findByDepartamentoId(Long departamentoId);
}