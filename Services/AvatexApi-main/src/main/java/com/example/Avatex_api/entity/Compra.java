package com.example.Avatex_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="compras")
public class Compra implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proveedor;
    private Date fechaRegistro;
    private String numComprobante;
    private Double montoTotal;
    private Date fechaPago;
    private String estado;
    @Transient
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleCompra> detalleCompras;

    public Compra(Long id, String proveedor, Date fechaRegistro, String numComprobante, Double montoTotal,
                  Date fechaPago, String estado, List<DetalleCompra> detalleCompras) {
        this.id = id;
        this.proveedor = proveedor;
        this.fechaRegistro = fechaRegistro;
        this.numComprobante = numComprobante;
        this.montoTotal = montoTotal;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.detalleCompras = detalleCompras;
    }

    public Compra(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumComprobante() {
        return numComprobante;
    }

    public void setNumComprobante(String numComprobante) {
        this.numComprobante = numComprobante;
    }

    public Double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DetalleCompra> getDetalleCompras() {
        return detalleCompras;
    }

    public void setDetalleCompras(List<DetalleCompra> detalleCompras) {
        this.detalleCompras = detalleCompras;
    }
}
