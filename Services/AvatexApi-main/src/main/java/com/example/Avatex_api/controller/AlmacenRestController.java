package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.almacen.DividirRequestDto;
import com.example.Avatex_api.dto.almacen.PiezaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.service.IAlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/almacenamiento")
@RestController
public class AlmacenRestController {
    @Autowired
    private IAlmacenService almacenService;

    //ADMIN - USER
    @GetMapping("/movimientos")
    public ResponseEntity<?> obtenerMovimientos(){
        return ResponseEntity.ok().body(almacenService.obtenerMovimientos());
    }

    //ADMIN - USER
    @PostMapping("/piezas")
    public ResponseEntity<?> obtenerPiezas(@RequestBody IdRequestDto requestDto, Pageable pageable){
        return ResponseEntity.ok().body(almacenService.findByProducto(requestDto, pageable));
    }


    //ADMIN
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody PiezaRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.save(requestDto));
    }

    //ADMIN
    @PutMapping("/retirar")
    public ResponseEntity<?> retirar(@RequestBody IdRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.retirar(requestDto));
    }

    @PutMapping("/dividir")
    public ResponseEntity<?> dividir(@RequestBody DividirRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.dividir(requestDto));
    }

    @PutMapping("/anular")
    public ResponseEntity<?> anular(@RequestBody IdRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.anular(requestDto));
    }
/*
    @PutMapping("/anularRetiro")
    public ResponseEntity<?> anularRetiro(@RequestBody IdRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.anularRetiro(requestDto));
    }

    @PutMapping("/anularDividir")
    public ResponseEntity<?> anularDividir(@RequestBody DividirRequestDto requestDto){
        return ResponseEntity.ok().body(almacenService.anularDividir(requestDto));
    }
*/

}
