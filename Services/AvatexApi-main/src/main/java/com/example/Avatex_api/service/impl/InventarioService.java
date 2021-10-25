package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IKardexDao;
import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.inventario.InventarioResponseDto;
import com.example.Avatex_api.dto.inventario.KardexRequestDto;
import com.example.Avatex_api.dto.inventario.KardexResponseDto;
import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.service.IInventarioService;
import com.example.Avatex_api.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InventarioService implements IInventarioService {

    @Autowired
    private IKardexDao kardexDao;

    @Autowired
    private Utils utils;

    @Override
    public InventarioResponseDto obtenerInventario(AnioMesRequestDto requestDto, Pageable pageable) {
        List<Kardex> listaKardex = kardexDao.findByMes(requestDto.getAnio(), requestDto.getMes(), pageable);
        log.info("MES: "+requestDto.getMes()+ requestDto.getAnio());
        List<KardexResponseDto> listaDto = new ArrayList<>();
        InventarioResponseDto response = new InventarioResponseDto();
        int i=0;
        for (Kardex k:listaKardex ) {
            listaDto.add(utils.parseKardexResponse(k));
            log.info("KARDEX BD: " + listaKardex);
            log.info("PAGEABLE: " + pageable.toString());
            log.info("KARDEX RESPONSE: " + listaDto.get(i));
            i++;
        }
        response.setListaKardex(listaDto);
        response.setMes(utils.getMesActual());
        return response;
    }

    @Override
    public KardexResponseDto obtenerKardex(KardexRequestDto requestDto) {
        String year = requestDto.getFecha().getAnio();
        String month = requestDto.getFecha().getMes();
        String producto = requestDto.getProducto();

        Kardex kardex = kardexDao.findByMesProducto(year,month,producto);
        log.info("MES: "+month+" "+year+" PRODUCTO: "+producto);

        return utils.parseKardexResponse(kardex);
    }

}
