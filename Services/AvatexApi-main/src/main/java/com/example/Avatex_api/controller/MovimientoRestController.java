package com.example.Avatex_api.controller;

import com.example.Avatex_api.entity.Movimiento;
import com.example.Avatex_api.service.IMovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class MovimientoRestController {
    @Autowired
    private IMovimientoService movimientosService;

    //BORRAR
    @GetMapping("/movimientos")
    public List<Movimiento> index() {
        return movimientosService.findAll();
    }

    //ADMIN
    @GetMapping("/movimientos/fecha/{fecha}")
    public List<Movimiento> finByDate(@PathVariable Date fecha) {
        return movimientosService.findByDate(fecha);
    }

    //ADMIN
    @GetMapping("/movimientos/nombre/{nombre}")
    public List<Movimiento> findByName(@PathVariable String nombre) {
        return movimientosService.findByProduct(nombre);
    }

    //USER
    @PostMapping("/movimientos/pedido")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento SolicitarRetiro (@RequestBody Movimiento movimiento) throws Exception {
        movimiento.setFecha(new Date());
        return movimientosService.SolicitarRetiro(movimiento);
    }

    //ADMIN
    @PostMapping("/movimientos/retiro")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento Retiro (@RequestBody Movimiento movimiento) throws Exception {
        movimiento.setFecha(new Date());
        return movimientosService.Retiro(movimiento);
    }

    //ADMIN
    @PutMapping("/movimientos/retiro/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento ConfirmarRetiro (@PathVariable Long id) {
            Movimiento movimiento = movimientosService.findByID(id);
            movimiento.setFecha(new Date());
            return movimientosService.Retiro(movimiento);
    }

    //USER
    @PutMapping("/movimientos/cancel/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movimiento Cancel (@PathVariable Long id) {
            return movimientosService.cancel(id);
    }


}