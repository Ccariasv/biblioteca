package com.umg.biblioteca.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "estado_lb")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EstadoLb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "estado", nullable = false, length = Integer.MAX_VALUE)
    private String estado;

}