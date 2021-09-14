package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.service.ICompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CompraRestController {

    @Autowired
    private ICompraService compraService;

    @GetMapping("/compras")
    public List<Compra> findAll(){
        return compraService.findCompras();
    }

    @GetMapping("/compras/{id}")
    public Compra findById(Long id){
        return compraService.findCompraByID(id);
    }

   /* @GetMapping("/compras/{fechaPago}")
    public List<Compra> findByFechaPago(Date fechaPago){
        return compraService.findCompraByFechaPago(fechaPago);
    }*/

    /*@GetMapping("/compras/:mes")
    public List<Compra> findByMonth(String mes){
        return compraService.findCompraByMes(mes);
    }

    @GetMapping("/compras/:Proveedor")
    public List<Compra> findByProveedor(String proveedor){
        return compraService.findCompraByProveedor(proveedor);

    }*/

    @PostMapping("/compras")
    public Compra create(Compra compra){
        return  compraService.save(compra);
    }

    //Actualizar
    @PutMapping("/compras/{id}")
    public Compra update(Long id){
        return compraService.update(id);
    }

    /*
    @DeleteMapping("/compras/{id}")
    public Compra delete(Long id){
        return compraService.cancel(id);
    }
    */

}
