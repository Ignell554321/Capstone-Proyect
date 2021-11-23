package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.dto.venta.VentaRequestDto;
import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.Venta;
import com.example.Avatex_api.service.IVentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api/venta")
@RestController
public class VentaRestController {

    @Autowired
    private IVentaService ventaService;

    @GetMapping
    public List<Venta> findAll() {
        return ventaService.findVentas();
    }
    
    @GetMapping("/search/active")
    public ResponseEntity<?> listarXstatus (Pageable pageable){
        return ResponseEntity.ok().body(ventaService.findAll(pageable));
    }
    
    @GetMapping("/search")
    public ResponseEntity<?> listar (Pageable pageable){
        return ResponseEntity.ok().body(ventaService.findAll(pageable));
    }

    @PostMapping("/search/date")
    public ResponseEntity<?> buscarXfecha (@RequestBody BuscaXFechaRequestDto requestDto, Pageable pageable){
        return ResponseEntity.ok(ventaService.findByDate(requestDto,pageable));
    }

    @PostMapping("/search/month")
    public ResponseEntity<?> buscarXMes (@RequestBody AnioMesRequestDto requestDto, Pageable pageable){
        return ResponseEntity.ok(ventaService.findByMonth(requestDto,pageable));
    }

    @PostMapping("")
    public ResponseEntity<?> registrar (@RequestBody VentaRequestDto requestDto) throws Exception {
        return ResponseEntity.ok(ventaService.save(requestDto));
    }

    @PutMapping("/cancel")
    public ResponseEntity<?> anular(@RequestBody IdRequestDto requestDto){
        return ResponseEntity.ok(ventaService.anular(requestDto.getId()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerXId(@PathVariable("id") Long id){
    	
    	return ResponseEntity.ok(ventaService.findVentaByID(id));
    }

}
