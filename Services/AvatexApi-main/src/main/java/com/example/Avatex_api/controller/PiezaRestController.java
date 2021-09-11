package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IPiezaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/pieza/pagina")
	public ResponseEntity<?> listar(Pageable pageable) 
	{
		return ResponseEntity.ok().body(productoService.findAll(pageable));
	}

    //ADMIN - USER
    @GetMapping("/pieza/producto/{id}")
    public ResponseEntity<?>  findByProducto(@PathVariable Long id,Pageable pageable) {
    	Producto p=new Producto();
    	p.setId(id);
        return ResponseEntity.ok().body(productoService.findByProducto(p,pageable));
    }
    
    //ADMIN - USER
    @GetMapping("/pieza/color/{color}")
    public ResponseEntity<?>  findByColor(@PathVariable String color,Pageable pageable) {
        return ResponseEntity.ok().body(productoService.findByColor(color,pageable));
    }

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
