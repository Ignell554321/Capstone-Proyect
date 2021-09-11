package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoDao extends JpaRepository<Producto,Long> {

}