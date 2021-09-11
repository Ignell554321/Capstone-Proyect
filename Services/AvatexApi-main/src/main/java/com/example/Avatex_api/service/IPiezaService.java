package com.example.Avatex_api.service;

import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPiezaService {

    public List<Pieza> findPiezas();
    public Page<Pieza> findAll(Pageable pageable);
    public Pieza findPiezaByID(Long id);
    public List<Pieza> findPiezaByName(String nombre);
    public Pieza save(Pieza pieza);
    public Page<Pieza> findByProducto(Producto producto,Pageable pageable);
    public Page<Pieza> findByColor(String color,Pageable pageable);
}