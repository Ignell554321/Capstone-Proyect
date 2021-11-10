package com.example.Avatex_api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.service.IPiezaService;

@Service
public class PiezaServiceImpl implements IPiezaService{

	@Autowired
	private IPiezaDao repository;
	
	@Override
	public Page<Pieza> findByProducto(Long idProducto, Pageable pageable) {
	
		return repository.findByProducto(idProducto, pageable);
	}

}
