package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dao.IVentaDao;
import com.example.Avatex_api.entity.Venta;
import com.example.Avatex_api.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IPiezaDao piezaDao;
    @Autowired
    private IVentaDao ventaDao;


    @Override
    public List<Venta> findVentas() {
        return (List<Venta>) ventaDao.findAll();
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return ventaDao.findAll(pageable);
    }

    @Override
    public Venta findVentaByID(Long id) {
        return ventaDao.findById(id).orElse(null);
    }

    @Override
    public Venta save(Venta venta) {
        return null;
    }

    @Override
    public Venta update(Venta venta) {
        return null;
    }

    @Override
    public Venta delete(Long id) {
        return null;
    }
}
