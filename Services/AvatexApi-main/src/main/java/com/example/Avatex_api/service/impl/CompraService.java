package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IComprasDao;
import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.compra.ProveedorRequestDto;
import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.service.ICompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompraService implements ICompraService{

    @Autowired
    private IPiezaDao piezaDao;
    @Autowired
    private IComprasDao compraDao;


    @Override
    public List<Compra> findCompras() {
        return (List<Compra>) compraDao.findAll();
    }

    @Override
    public Compra findCompraByID(Long id) {
        return compraDao.findById(id).orElse(null);
    }

    @Override
    public List<Compra> findCompraByFechaPago(Date fechaPago) {
        return null;//(List<Compra>) compraDao.findByFechaPago(fechaPago);
    }

    @Override
    public Page<Compra> findByMonth(AnioMesRequestDto requestDto, Pageable pageable) {

        int year = Integer.parseInt(requestDto.getAnio());
        int month = requestDto.calcularNroMes();
        Page<Compra> compras = compraDao.findAllByMonth(year,month,pageable);
        return compras;
    }

    @Override
    public Page<Compra> findByProveedor(ProveedorRequestDto requestDto, Pageable pageable) {
        Page<Compra> compras = compraDao.findByProveedor(requestDto.getNombre(), pageable);
        return compras;
    }

    @Override
    public Compra save(Compra compra) {
        return compraDao.save(compra);
    }

    /*
    @Override
    public Compra update(Long id) {
        Compra compra = new Compra();
        compra = compraDao.findById(id).orElse(null);
        return compraDao.save(compra);
    }
    */

	@Override
	public Page<Compra> findAll(Pageable pageable) {

		return compraDao.findAll(pageable);
	}

	@Override
	public Page<Compra> findByFechaPagoAndEstado(String fechaPago, Pageable pageable) {

		return compraDao.findByFechaPagoAndEstado(fechaPago,"CREADO", pageable);
	}

	@Override
	public Page<Compra> findByEstado(String estado, Pageable pageable) {

		return compraDao.findByEstado(estado, pageable);
	}

	@Override
	public Compra anularCompra(Long id) {

		Compra compraDB = findCompraByID(id);
		if (compraDB == null) {
			return null;
		}
		compraDB.setEstado("ANULADO");
		return compraDao.save(compraDB);
	}




	
}
