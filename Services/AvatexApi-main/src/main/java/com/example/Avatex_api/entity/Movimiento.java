package com.example.Avatex_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="movimientos")
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private Double cantidad;
    private String tipoOperacion;    // retiro - dividir - anularRetiro -anularOperacion
    private String usuario;
    @JoinColumn(name="pieza_id")
    @OneToOne
    private Pieza pieza;


}
