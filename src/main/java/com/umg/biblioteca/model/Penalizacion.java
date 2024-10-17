package com.umg.biblioteca.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "penalizacion")
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

    @Column(name = "tiempo_penalizacion", nullable = false, precision = 8, scale = 2)
    private BigDecimal tiempoPenalizacion;

    @Column(name = "fecha_penalizacion", nullable = false)
    private Long fechaPenalizacion;

}