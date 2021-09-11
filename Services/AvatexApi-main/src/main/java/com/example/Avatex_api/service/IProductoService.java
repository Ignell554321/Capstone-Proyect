package com.example.Avatex_api.service;

import java.util.List;

import com.example.Avatex_api.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAllProductos();
}
