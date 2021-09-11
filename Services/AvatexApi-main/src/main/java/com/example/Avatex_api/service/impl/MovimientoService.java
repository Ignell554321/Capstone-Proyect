package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IMovimientoDao;
import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.entity.Movimiento;
import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.service.IMovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

@Service
public class MovimientoService implements IMovimientoService{
    @Autowired
    private IMovimientoDao movimientoDao;
    @Autowired
    private IPiezaDao piezaDao;

    @Override
    public List<Movimiento> findAll() {

        return (List<Movimiento>) movimientoDao.findAll();
    }


    @Override
    public Movimiento findByID(Long id) {
        return movimientoDao.findById(id).orElse(null);
    }

    //Filtra los productos por fecha
    @Override
    public List<Movimiento> findByDate(Date fecha) {
        List<Movimiento> movimientos = (List<Movimiento>) movimientoDao.findAll();
        /*
        List<Movimiento> movimientosPorDia = new ArrayList<Movimiento>();
        for (Movimiento m:movimientos) {
            if(m.getFecha() == fecha) movimientosPorDia.add(m);
        }*/

        Predicate<Movimiento> diferenteFecha = movimiento -> movimiento.getFecha()!=fecha;
        movimientos.removeIf(diferenteFecha);
        return movimientos;
    }

    //Filtra los productos por nombre
    @Override
    public List<Movimiento> findByProduct(String nombre) {

        List<Movimiento> movimientos = (List<Movimiento>) movimientoDao.findAll();
        Predicate<Movimiento> diferenteNombre= movimiento -> movimiento.getPieza().getProducto().getNombre()!=nombre;
        movimientos.removeIf(diferenteNombre);
        return movimientos;
    }


    @Override
    public Movimiento Retiro(Movimiento movimiento) {
        Pieza pieza = piezaDao.findByID(movimiento.getPieza().getId());
        pieza.setMetraje(pieza.RestarMetraje(movimiento.getCantidad()));
        piezaDao.save(pieza);
        movimiento.setEstado("Retirado");
        return movimientoDao.save(movimiento);
    }

    @Override
    public Movimiento SolicitarRetiro(Movimiento movimiento) {
        if(movimiento.getUnidad().equals("Pieza")){
            movimiento.setCantidad(movimiento.getPieza().getMetraje());
        }
        return movimientoDao.save(movimiento);
    }

    @Override
    public Movimiento cancel(Long id) {
        Movimiento movimiento = movimientoDao.findById(id).orElse(null);
        movimiento.setEstado("Cancelado");
        return movimientoDao.save(movimiento);
    }

}
