package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dao.IProductoDao;
import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IPiezaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PiezaService implements IPiezaService {
    @Autowired
    private IPiezaDao piezaDao;
   

    //Metodos Pieza

    @Override
    public List<Pieza> findPiezas() {
        return (List<Pieza>) piezaDao.findAll();
    }

    @Override
    public Pieza findPiezaByID(Long id) {
        return piezaDao.findById(id).orElse(null);
    }

    @Override
    public List<Pieza> findPiezaByName(String nombre) {
        return piezaDao.findByName(nombre);
    }

    @Override
    public Pieza save(Pieza pieza) {
        return piezaDao.save(pieza);
    }

	@Override
	public Page<Pieza> findAll(Pageable pageable) {

		return piezaDao.findAll(pageable);
	}

	@Override
	public Page<Pieza> findByProducto(Producto producto,Pageable pageable) {
		// TODO Auto-generated method stub
		return  piezaDao.findByProducto(producto,pageable);
	}

	@Override
	public Page<Pieza> findByColor(String color, Pageable pageable) {
		// TODO Auto-generated method stub
		return piezaDao.findByColor(color, pageable);
	}



}
