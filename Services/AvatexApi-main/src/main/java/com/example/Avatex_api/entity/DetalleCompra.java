package com.example.Avatex_api.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

@Entity
@Table(name="detallecompras")
public class DetalleCompra {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long producto_id;
    private String nombreProducto;
    private Double cantidad;
    private Double precio;
    private Double subTotal;
    

    public DetalleCompra(Long id, String nombreProducto, Compra compra, Double cantidad, Double subTotal, Double precio) {
        this.id = id;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.precio=precio;
    }

	@Override
	public boolean equals(Object obj){
		
		if(this==obj) {
			return true;
		}
		
		if(!(obj instanceof DetalleCompra)) {
			return false;
		}
		
		DetalleCompra a = (DetalleCompra) obj;
		
		return this.id!=null && this.id.equals(a.getId());
		
	}
	
	
	
	public Long getProducto_id() {
		return producto_id;
	}

	public void setProducto_id(Long producto_id) {
		this.producto_id = producto_id;
	}

	public void calcularSubTotal() {
		this.subTotal= this.cantidad * this.precio;
	}
	
    public DetalleCompra(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
    
    
}



