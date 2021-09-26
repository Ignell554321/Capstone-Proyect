package com.example.Avatex_api.dto.venta;

public class DetalleVentaRequestType {

    private String producto;
    private double metraje;
    private int idProducto;
    private double precio;
    private double subtotal;

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getMetraje() {
        return metraje;
    }

    public void setMetraje(double metraje) {
        this.metraje = metraje;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
