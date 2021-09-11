package com.example.Avatex_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IPiezaService;
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

	
}
