package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IKardexDao;
import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.dto.inventario.InventarioResponseDto;
import com.example.Avatex_api.dto.inventario.KardexResponseDto;
import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IInventarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private IKardexDao kardexDao;

    @Override
    public InventarioResponseDto obtenerInventario(AnioMesRequestDto requestDto, Pageable pageable) {
        List<Kardex> listaKardex = kardexDao.findByMes(requestDto.getAnio(), requestDto.getMes(), pageable);
        log.info("MES: "+requestDto.getMes()+ requestDto.getAnio());
        List<KardexResponseDto> listaDto = new ArrayList<>();
        InventarioResponseDto response = new InventarioResponseDto();
        int i=0;
        for (Kardex k:listaKardex ) {
            listaDto.add(setKardexResponse(k));
            log.info("KARDEX BD: " + listaKardex);
            log.info("PAGEABLE: " + pageable.toString());
            log.info("KARDEX RESPONSE: " + listaDto.get(i));
            i++;
        }
        response.setListaKardex(listaDto);
        response.setMes(getMesActual());
        return response;
    }

    @Scheduled(cron="0 0 0 1 1-12 ?")
    private void generarKardexDelMes (){

        List<Kardex> kardexList = new ArrayList<>();
        if(kardexList.size()==0 || kardexList == null)
        {
            //Probablemente esto se realize al registrar un nuevo producto
            List<Producto> productos = new ArrayList<>();
            for (Producto p:productos ) {
                setKardexFirst(p);
            }
        }else{
            for (Kardex k:kardexList ) {
                setKardex(k);
            }

        }
    }


    private KardexResponseDto setKardexResponse(Kardex kardex){

        return KardexResponseDto.builder().
                descripcion(kardex.getProducto()).
                costo(kardex.getCosto()).
                compras(kardex.getTotalCompras()).
                ventas(kardex.getTotalVentas()).
                saldoAnterior(kardex.getSaldoMesAnterior()).
                saldoActual(kardex.getSaldoMesActual()).build();

    }

    private Kardex setKardex(Kardex kardexAnterior){
        return Kardex.builder().
                producto(kardexAnterior.getProducto()).
                mes(getMesActual()).
                anio(getAñoActual()).
                saldoMesAnterior(kardexAnterior.getSaldoMesAnterior()).
                saldoMesActual(kardexAnterior.getSaldoMesAnterior()).
                build();

    }
    private Kardex setKardexFirst(Producto producto){

        return Kardex.builder().
                producto(producto.getNombre()).
                mes(getMesActual()).
                anio(getAñoActual()).
                build();

    }

    private String getMesActual(){
        Date date = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        log.info("MES: "+month.format(date).toUpperCase());
        return month.format(date).toUpperCase();
    }
    private String getAñoActual(){
        Date date = new Date();
        SimpleDateFormat year = new SimpleDateFormat("YYYY");
        log.info("AÑO: "+year.format(date).toUpperCase());
        return year.format(date).toUpperCase();
    }
}
