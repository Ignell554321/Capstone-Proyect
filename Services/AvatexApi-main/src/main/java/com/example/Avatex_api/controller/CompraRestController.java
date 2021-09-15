package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.DetalleCompra;
import com.example.Avatex_api.service.ICompraService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;

@CrossOrigin(origins = "*")
@RequestMapping("/compra")
@RestController
public class CompraRestController {

    @ElementCollection
    private List<DetalleCompra> listaDetalle=  new ArrayList<DetalleCompra>();
    
    @Autowired
    private ICompraService compraService;

    @GetMapping
    public List<Compra> findAll(){
        return compraService.findCompras();
    }

    @GetMapping("/{id}")
    public Compra findById(Long id){
        return compraService.findCompraByID(id);
    }
    

    @RequestMapping(value= {"/limpiarDetalles"},method=RequestMethod.GET)
	public ResponseEntity<?> limpiarDetalles() throws JsonProcessingException, ParseException {
	
    	this.listaDetalle.clear();
		return ResponseEntity.ok(listaDetalle);
	}
    
    @RequestMapping(value= {"/listarDetalles"},method=RequestMethod.GET)
	public ResponseEntity<?> listarDetalles() throws JsonProcessingException, ParseException {

    	return ResponseEntity.ok().body(listaDetalle);
	}
    
    @PostMapping(value = "/addDetalle" )
	public  ResponseEntity<?> addDetail(@RequestBody DetalleCompra detalle)
	{
    	detalle.calcularSubTotal();
		verificarDetalleRepetido(detalle);
		return  ResponseEntity.ok(detalle);
	}
	
	private void verificarDetalleRepetido(DetalleCompra detalle)
	{
		boolean existe=false;
		List<DetalleCompra> listaDetalleOpe=  new ArrayList<DetalleCompra>();
		
		for (DetalleCompra detalleOpe : listaDetalle) {
			
			if(detalle.getNombreProducto() == detalleOpe.getNombreProducto())
			{
				existe=true;
				listaDetalleOpe.add(detalle);
				
			}else {
				listaDetalleOpe.add(detalleOpe);
			}
		}
		
		if(existe)
		{
			listaDetalle=listaDetalleOpe;
		}else {
			listaDetalle.add(detalle);
		}
		
	}

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Compra compra){
    	
        return  ResponseEntity.ok(compraService.save(compra));
    }

    @PutMapping("/{id}")
    public Compra update(Long id){
        return compraService.update(id);
    }


}
