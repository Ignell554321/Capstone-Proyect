package com.example.Avatex_api.service;


import com.example.Avatex_api.entity.Compra;

import java.util.Date;
import java.util.List;

public interface ICompraService {

    public List<Compra> findCompras();
    public Compra findCompraByID(Long id);
    public List<Compra> findCompraByFechaPago(Date fecha);
    public List<Compra> findCompraByMes(String mes);
    public List<Compra> findCompraByProveedor(String proveedor);
    public Compra save(Compra compra);
    public Compra cancel(Long id);
}
