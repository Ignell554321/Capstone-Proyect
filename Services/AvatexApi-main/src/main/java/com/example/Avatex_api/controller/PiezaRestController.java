package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.service.IPiezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PiezaRestController {
    @Autowired
    private IPiezaService productoService;

    //BORRAR
    @GetMapping("/pieza")
    public List<Pieza> index() { return productoService.findPiezas();}


    //ADMIN - USER
    @GetMapping("/pieza/{id}")
    public Pieza show(@PathVariable Long id) {
        return productoService.findPiezaByID(id);
    }

  /*  //ADMIN - USER
    @GetMapping("/pieza/{nombre}")
    public List<Pieza> findByName(@PathVariable String nombre) {
        return productoService.findPiezaByName(nombre);
    }*/

    //ADMIN
    @PostMapping("/pieza")
    @ResponseStatus(HttpStatus.CREATED)
    public Pieza create(@RequestBody Pieza pieza){
        pieza.setFecha(new Date());
        return productoService.save(pieza);
    }

    //ADMIN
    @PutMapping("/pieza/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Pieza update(@RequestBody Pieza pieza, @PathVariable Long id){
        return productoService.save(pieza);
    }
    
    
}
