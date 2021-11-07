package com.example.Avatex_api.service;

import com.example.Avatex_api.dto.almacen.DividirRequestDto;
import com.example.Avatex_api.dto.almacen.PiezaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.entity.Movimiento;
import com.example.Avatex_api.entity.Pieza;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAlmacenService {

    public List<Pieza> findByProducto(IdRequestDto requestDto, Pageable pageable);
    public List<Movimiento> obtenerMovimientos();
    public Pieza save(PiezaRequestDto requestDto);
    public Pieza retirar(IdRequestDto requestDto);
    public Pieza dividir(DividirRequestDto requestDto);
    public Pieza anular(IdRequestDto requestDto);
    /*
    public Pieza anularRetiro(IdRequestDto requestDto);
    public Pieza anularDividir(DividirRequestDto requestDto);
    * */
}
