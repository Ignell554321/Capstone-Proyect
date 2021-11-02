package com.example.Avatex_api.utils;

import com.example.Avatex_api.dao.IKardexDao;
import com.example.Avatex_api.dao.IProductoDao;
import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IInventarioService;
import com.example.Avatex_api.service.impl.VentaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Automation {

    @Autowired
    private IInventarioService inventarioService;

    @Autowired
    private IProductoDao productoDao;

    @Autowired
    private IKardexDao kardexDao;

    @Autowired
    private Utils utils;

    @Scheduled(cron="0 0 0 1 1-12 ?")
    private void generarKardexDelMes (){

    	log.info("mensaje de prueba");

        List<Producto> productos = productoDao.findAll();

        for (Producto prod:productos) {

            Kardex kardexAnterior = kardexDao.findByMesProducto(utils.getAñoActual(),utils.getMesAnterior(), prod.getNombre());
            Kardex kardex = new Kardex();
            kardex.builder().
                    anio(utils.getAñoActual()).
                    mes(utils.getMesActual()).
                    producto(prod.getNombre()).
                    costo(kardexAnterior.getCosto()).
                    saldoMesAnterior(kardexAnterior.getSaldoMesActual()).
                    saldoMesActual(kardexAnterior.getSaldoMesActual()).
                    totalVentas(0).
                    totalCompras(0).
                    build();
            kardexDao.save(kardex);
        }


    }

}
