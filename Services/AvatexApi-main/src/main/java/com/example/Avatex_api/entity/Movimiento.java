package com.example.Avatex_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="movimientos")
public class Movimiento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fecha;
    private String unidad; // pieza - mts
    private Double cantidad;
    private String estado; // espera - finalizado
    @JoinColumn(name="pieza_id")
    @OneToOne
    private Pieza pieza;
    @JoinColumn(name="usuario_id")
    @OneToOne
    private Usuario usuario;

    public Movimiento(Date fecha, String unidad, Double cantidad, String estado, Pieza pieza, Usuario usuario) {
        this.fecha = fecha;
        this.unidad = unidad;
        this.cantidad = cantidad;
        this.estado = estado;
        this.pieza = pieza;
        this.usuario = usuario;
    }
    public Movimiento(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
