package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.service.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/Inventario")
@RestController
public class InventarioRestController {

    @Autowired
    private IInventarioService inventarioService;

    @PostMapping("/search/month")
    public ResponseEntity<?> buscarXMes (@RequestBody AnioMesRequestDto requestDto, Pageable pageable){
        return ResponseEntity.ok(inventarioService.obtenerInventario(requestDto,pageable));
    }
}
