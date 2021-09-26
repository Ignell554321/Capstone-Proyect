package com.example.Avatex_api.dto.venta;

import com.example.Avatex_api.entity.DetalleVenta;
import com.example.Avatex_api.entity.Usuario;

import java.util.Date;
import java.util.List;

public class VentaRequestDto {

    private double montoTotal;
    private String username;

    private List<DetalleVentaRequestType> listaDetalles;

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<DetalleVentaRequestType> getListaDetalles() {
        return listaDetalles;
    }

    public void setListaDetalles(List<DetalleVentaRequestType> listaDetalles) {
        this.listaDetalles = listaDetalles;
    }
}
