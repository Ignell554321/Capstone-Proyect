package com.example.Avatex_api.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="detallecompras")
public class DetalleCompra {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Pieza pieza;
    private Double cantidad;
    private Double subTotal;
    @JoinColumn(name="compra_id")
    @ManyToOne
    private Compra compra;

    public DetalleCompra(Long id, Pieza pieza, Compra compra, Double cantidad, Double subTotal) {
        this.id = id;
        this.pieza = pieza;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.compra = compra;
    }

    public DetalleCompra(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }
}



