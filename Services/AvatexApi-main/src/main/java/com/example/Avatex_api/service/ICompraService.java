package com.example.Avatex_api.service;

import com.example.Avatex_api.entity.Compra;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICompraService {

    public List<Compra> findCompras();
    public Page<Compra> findAll(Pageable pageable);
    public Compra findCompraByID(Long id);
    public List<Compra> findCompraByFechaPago(Date fecha);
    public List<Compra> findCompraByMes(String mes);
    public List<Compra> findCompraByProveedor(String proveedor);
    public Compra save(Compra compra);
    public Compra update(Long id);
    //public Page<Compra> findByfechaPago(String fechaPago,Pageable pageable);
    public Page<Compra> findByFechaPagoAndEstado(String fechaPago,Pageable pageable);
    public Page<Compra> findByEstado(String estado,Pageable pageable);
    public Compra deleteCompra(Compra compra);
    
}
