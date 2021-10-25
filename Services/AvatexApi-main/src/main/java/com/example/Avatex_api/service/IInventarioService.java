package com.example.Avatex_api.service;

import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.inventario.InventarioResponseDto;
import com.example.Avatex_api.dto.inventario.KardexRequestDto;
import com.example.Avatex_api.dto.inventario.KardexResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IInventarioService {

    public InventarioResponseDto obtenerInventario (AnioMesRequestDto requestDto, Pageable pageable);
    public KardexResponseDto obtenerKardex(KardexRequestDto requestDto);
}
