package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dto.almacen.PiezaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.service.IAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlmacenService implements IAlmacenService {

    @Autowired
    private IPiezaDao piezaDao;


    @Override
    public List<Pieza> findByProducto(IdRequestDto requestDto, Pageable pageable) {
        return  piezaDao.findByProducto(requestDto.getId(),pageable);
    }

    @Override
    public Pieza save(PiezaRequestDto requestDto) {
        Pieza pieza = Pieza.builder()
                .color(null)
                .fecha(new Date())
                .metraje(requestDto.getMetraje())
                .ubicacion("Almacen")
                .producto(requestDto.getProducto())
                .build();
        return piezaDao.save(pieza);
    }

    @Override
    public Pieza retirar(IdRequestDto requestDto) {

        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.setUbicacion("TIENDA");
        return piezaDao.save(pieza);
    }

    @Override
    public Pieza dividir(PiezaRequestDto requestDto) {

        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.RestarMetraje(requestDto.getMetraje());
        return piezaDao.save(pieza);
    }

    @Override
    public Pieza anularRetiro(IdRequestDto requestDto) {
        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.setUbicacion("ALMACEN");

        return piezaDao.save(pieza);
    }
}
