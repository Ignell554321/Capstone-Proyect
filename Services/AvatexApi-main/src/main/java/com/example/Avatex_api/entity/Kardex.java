package com.example.Avatex_api.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="kardex")
public class Kardex implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaInicio;

    private Date fechaFin;

    @Transient
    private List<Compra> compra;
    @Transient
    private List<Venta> venta;

}
