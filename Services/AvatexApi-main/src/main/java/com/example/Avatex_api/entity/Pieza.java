package com.example.Avatex_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="piezas")
public class Pieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double metraje;
    private String color;
    private String ubicacion; //Almacen - tienda
    private Date fecha; //fecha de registro
    @JoinColumn(name="producto_id")
    @OneToOne
    private Producto producto;

    public void RestarMetraje(Double cantidad){
        metraje = this.getMetraje() - cantidad;
    }

    public void AgregarMetraje(Double cantidad){
        metraje = this.getMetraje() + cantidad;
    }
}
