package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IComprasDao;
import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.service.ICompraService;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Compra> findCompraByMes(String mes) {
        return null;//(List<Compra>) compraDao.findByMes(mes);
    }

    @Override
    public List<Compra> findCompraByProveedor(String proveedor) {
        return null;//(List<Compra>) compraDao.findByProveedor(proveedor);
    }

    @Override
    public Compra save(Compra compra) {
        return compraDao.save(compra);
    }

    @Override
    public Compra cancel(Long id) {
        Compra compra = new Compra();
        compra = compraDao.findById(id).orElse(null);
        return compraDao.save(compra);
    }
}
