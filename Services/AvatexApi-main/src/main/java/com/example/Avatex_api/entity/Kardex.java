package com.example.Avatex_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="kardex")
public class Kardex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String producto;
    private String anio;
    private String mes;
    private double saldoMesAnterior;
    private double saldoMesActual;
    private double costo;
    private double totalCompras;
    private double totalVentas;

    @Transient
    private List<Compra> compra;
    @Transient
    private List<Venta> venta;

    public void aumentarSaldo(double cantidad){
        saldoMesActual += cantidad;
    }

    public void disminuirSaldo(double cantidad){
        saldoMesActual -= cantidad;
    }

    public void aumentarTotalCompras(double cantidad){
        totalCompras += cantidad;
    }

    public void aumentarTotalVentas(double cantidad){
        totalVentas += cantidad;
    }

}
