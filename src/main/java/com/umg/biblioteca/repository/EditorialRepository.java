package com.umg.biblioteca.repository;

import com.umg.biblioteca.model.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
}