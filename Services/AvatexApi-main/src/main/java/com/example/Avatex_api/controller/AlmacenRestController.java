package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.almacen.PiezaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.service.IAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class AlmacenRestController {
    @Autowired
    private IAlmacenService almacenService;

    //ADMIN - USER
    @GetMapping("/pieza")
    public ResponseEntity<?> obtenerPiezas(IdRequestDto requestDto, Pageable pageable)
    {
        return ResponseEntity.ok().body(almacenService.findByProducto(requestDto, pageable));
    }


    //ADMIN
    @PostMapping("/pieza")
    public ResponseEntity<?> registrar(PiezaRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.save(requestDto));
    }

    //ADMIN
    @PutMapping("/pieza")
    public ResponseEntity<?> retirar(IdRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.retirar(requestDto));
    }

    @PutMapping("/pieza")
    public ResponseEntity<?> dividir(PiezaRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.dividir(requestDto));
    }

    @PutMapping("/pieza")
    public ResponseEntity<?> anularRetiro(IdRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.anularRetiro(requestDto));
    }

}
