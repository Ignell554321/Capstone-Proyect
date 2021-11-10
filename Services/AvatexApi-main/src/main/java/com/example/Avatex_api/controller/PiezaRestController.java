package com.example.Avatex_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Avatex_api.service.IPiezaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Pieza")
public class PiezaRestController {
	
	@Autowired
	private IPiezaService service;
	
	@GetMapping("/pagina/{idProducto}")
	public ResponseEntity<?> paginado(@PathVariable Long idProducto,Pageable pageable){
		return ResponseEntity.ok(service.findByProducto(idProducto, pageable));
	}
	

}
