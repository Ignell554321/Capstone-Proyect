package com.example.Avatex_api.service;

import com.example.Avatex_api.entity.Movimiento;

import java.util.Date;
import java.util.List;

public interface IMovimientoService {
    public List<Movimiento> findAll();
    public Movimiento findByID(Long id);
    public List<Movimiento> findByDate(Date fecha);
    public List<Movimiento> findByProduct(String nombre);
    public Movimiento Retiro(Movimiento movimiento);
    public Movimiento SolicitarRetiro(Movimiento movimiento);
    public Movimiento cancel(Long id);
}