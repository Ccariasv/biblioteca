package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Penalizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PenalizacionRepository extends JpaRepository<Penalizacion, Long> {
}