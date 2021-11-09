package com.example.Avatex_api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.Avatex_api.entity.Pieza;

public interface IPiezaService {
	
	public Page<Pieza> findByProducto(Long idProducto,Pageable pageable);

}
