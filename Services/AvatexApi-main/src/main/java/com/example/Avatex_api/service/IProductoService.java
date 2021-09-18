package com.example.Avatex_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.Avatex_api.entity.Producto;

public interface IProductoService {
	
	public List<Producto> findAllProductos();
	public Page<Producto> findAll(Pageable pageable);
}
