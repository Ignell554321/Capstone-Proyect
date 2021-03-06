package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.inventario.KardexRequestDto;

import com.example.Avatex_api.service.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class InventarioRestController {

    @Autowired
    private IInventarioService inventarioService;

    @PostMapping("/inventario/search/inventario")
    public ResponseEntity<?> buscarInventario (@RequestBody AnioMesRequestDto requestDto, Pageable pageable){
        return ResponseEntity.ok(inventarioService.obtenerInventario(requestDto,pageable));
    }
    @PostMapping("/inventario/search/kardex")
    public ResponseEntity<?> buscarKardex (@RequestBody KardexRequestDto requestDto){
        return ResponseEntity.ok(inventarioService.obtenerKardex(requestDto));
    }
}
