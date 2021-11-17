package com.example.Avatex_api.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name="ventas")
public class Venta implements Serializable{
	
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
	private double montoTotal;
	private String estado;

	//@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@JoinColumn(name="usuario_id")
	private String usuario;
	//@Transient
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

	public void setCurrentDate() {
		this.fechaRegistro=new Date();
	}
	
	 public Venta(){
    	 this.detalleVentas=new ArrayList<>();
    }

/*

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<DetalleVenta> getDetalleVentas() {
		return detalleVentas;
	}
	public void setDetalleVentas(List<DetalleVenta> detalleVentas) {
		this.detalleVentas = detalleVentas;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	*/
	
}
