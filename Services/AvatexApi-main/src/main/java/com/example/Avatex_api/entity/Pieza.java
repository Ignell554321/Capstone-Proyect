package com.example.Avatex_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.security.PrivateKey;
import java.util.Date;

@Entity
@Table(name="piezas")
public class Pieza implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double metraje;
    private String color;
    private String ubicacion;
    private Date fecha; //fecha de registro
    @JoinColumn(name="producto_id")
    @OneToOne
    private Producto producto;

    public Pieza(Double metraje, String color, String ubicacion, Date fecha, Producto producto) {
        this.metraje = metraje;
        this.color = color;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.producto = producto;
    }

    public Pieza(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMetraje() {
        return metraje;
    }

    public void setMetraje(Double metraje) {
        this.metraje = metraje;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Double RestarMetraje(Double cantidad){
        Double resto = this.getMetraje() - cantidad;
        return resto;
    }
}
