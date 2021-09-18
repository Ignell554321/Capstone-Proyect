package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.DetalleCompra;
import com.example.Avatex_api.service.ICompraService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    
    @GetMapping("/pagina")
 	public ResponseEntity<?> listar(Pageable pageable) 
 	{
 		return ResponseEntity.ok().body(compraService.findAll(pageable));
 	}
    
	@PostMapping("/eliminarDetalle")
	public ResponseEntity<?> eliminarDetalle(@RequestBody DetalleCompra detalleCompra) throws JsonParseException,IOException{
		
		int indice=0;
		int cont=0;
		for(DetalleCompra detalle:listaDetalle)
		{
			if (detalle.getProducto_id()==detalleCompra.getProducto_id()){
			
            	indice=cont;
            	
            }
			cont++;
		}
		listaDetalle.remove(indice);
		
		return ResponseEntity.ok().body(listaDetalle);
	}

    @GetMapping("/{id}")
    public Compra findById(@PathVariable("id") Long id){
    	
    	Compra c= compraService.findCompraByID(id);
    	this.listaDetalle= c.getDetalleCompras();
    	return c;
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
			
			if(detalle.getProducto_id() == detalleOpe.getProducto_id())
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
