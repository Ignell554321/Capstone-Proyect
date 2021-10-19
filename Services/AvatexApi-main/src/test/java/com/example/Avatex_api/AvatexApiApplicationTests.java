package com.example.Avatex_api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.service.impl.CompraService;

@SpringBootTest
class AvatexApiApplicationTests {

	@Autowired
	private CompraService compraService;
	
	@Test
	void contextLoads() {
		
		Long idCompra=(long) 5;
		
		Compra compra = compraService.findCompraByID(idCompra);
		Compra CompraResult= new Compra();
		
		Assertions.assertEquals(compra,CompraResult);
		
	}

}
