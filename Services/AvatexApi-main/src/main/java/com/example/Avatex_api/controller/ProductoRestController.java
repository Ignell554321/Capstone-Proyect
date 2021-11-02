package com.example.Avatex_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IProductoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;

	@GetMapping("/productos")
	public List<Producto> findAll(){
		return productoService.findAllProductos();
	}

	  
	@GetMapping("/productos/pagina")
	public ResponseEntity<?> paginado(Pageable pageable){
		return ResponseEntity.ok(productoService.findAll(pageable));
	}

	@PostMapping("/productos")
	public ResponseEntity<?> registrar(@RequestBody Producto producto){
		return ResponseEntity.ok(productoService.save(producto));
	}

	@PutMapping("/productos")
	public ResponseEntity<?> actualizar(@RequestBody Producto producto){
		return ResponseEntity.ok(productoService.update(producto));
	}
}
