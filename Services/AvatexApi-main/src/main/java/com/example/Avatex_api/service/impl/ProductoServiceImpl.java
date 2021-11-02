package com.example.Avatex_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Avatex_api.dao.IProductoDao;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{

	@Autowired
	private IProductoDao productoDao;

	
	@Override
	public List<Producto> findAllProductos() {
		
		return productoDao.findAll();
	}

	@Override
	public Page<Producto> findAll(Pageable pageable) {
		
		return productoDao.findAll(pageable);
	}

	@Override
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	public Producto update(Producto producto) {
		return productoDao.save(producto);
	}


}
