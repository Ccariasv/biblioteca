package com.umg.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "penalizacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Penalizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "prestamos_id", nullable = false)
    private Prestamo prestamos;

    @Column(name = "tiempo_penalizacion", nullable = false)
    private LocalDate tiempoPenalizacion;

    @Column(name = "fecha_penalizacion", nullable = false)
    private LocalDate fechaPenalizacion;
}