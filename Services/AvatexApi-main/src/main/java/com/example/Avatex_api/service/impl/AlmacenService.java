package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IMovimientoDao;
import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dao.IProductoDao;
import com.example.Avatex_api.dto.almacen.DividirRequestDto;
import com.example.Avatex_api.dto.almacen.PiezaRequestDto;
import com.example.Avatex_api.dto.common.IdRequestDto;
import com.example.Avatex_api.entity.Movimiento;
import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IAlmacenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class AlmacenService implements IAlmacenService {

    @Autowired
    private IPiezaDao piezaDao;
    @Autowired
    private IMovimientoDao movimientoDao;
    @Autowired
    private IProductoDao productoDao;


    @Override
    public List<Pieza> findByProducto(IdRequestDto requestDto, Pageable pageable) {
        return  (List<Pieza>) piezaDao.findByProducto(requestDto.getId(),pageable);
    }

    @Override
    public List<Movimiento> obtenerMovimientos() {
        return (List<Movimiento>) movimientoDao.findAll();
    }

    @Override
    public Pieza save(PiezaRequestDto requestDto) {

        Producto producto = productoDao.findById(requestDto.getIdProducto()).orElse(null);
        Pieza response = piezaDao.save(Pieza.builder()
                .color(requestDto.getColor())
                .fecha(new Date())
                .metraje(requestDto.getMetraje())
                .ubicacion("ALMACEN")
                .producto(producto)
                .build());

        log.info("PIEZA: "+response);
        log.info("PRODUCTO: "+response.getProducto());
        if(response != null){
            Movimiento mov = movimientoDao.save(Movimiento.builder()
                    .fecha(new Date())
                    .cantidad(response.getMetraje())
                    .tipoOperacion("INGRESO")
                    .usuario("")
                    .pieza(response)
                    .build());
            log.info("MOVIMIENTO: "+mov);
        }
        log.info("FIN DEL PROCESO");
        return response;
    }

    @Override
    public Pieza retirar(IdRequestDto requestDto) {

        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.setUbicacion("TIENDA");
        Pieza response = piezaDao.save(pieza);
        if( response!= null){
            Movimiento mov = movimientoDao.save(Movimiento.builder()
                    .fecha(new Date())
                    .cantidad(pieza.getMetraje())
                    .tipoOperacion("RETIRO")
                    .usuario("")
                    .pieza(response)
                    .build());
        }

        return response;
    }

    @Override
    public Pieza dividir(DividirRequestDto requestDto) {

        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.RestarMetraje(requestDto.getMetraje());
        Pieza response = piezaDao.save(pieza);
        if( response!= null){
            Movimiento mov = movimientoDao.save(Movimiento.builder()
                    .fecha(new Date())
                    .cantidad(requestDto.getMetraje())
                    .tipoOperacion("DIVIDIR")
                    .usuario("")
                    .pieza(response)
                    .build());
        }
        return response;
    }
/*
    @Override
    public Pieza anularRetiro(IdRequestDto requestDto) {
        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.setUbicacion("ALMACEN");
        Pieza response = piezaDao.save(pieza);
        if( response!= null){
            Movimiento mov = movimientoDao.save(Movimiento.builder()
                    .fecha(new Date())
                    .cantidad(pieza.getMetraje())
                    .tipoOperacion("ANULAR RET")
                    .usuario("")
                    .pieza(response)
                    .build());
        }

        return response;
    }

    @Override
    public Pieza anularDividir(DividirRequestDto requestDto) {
        Pieza pieza = piezaDao.findByID(requestDto.getId());
        pieza.AgregarMetraje(requestDto.getMetraje());
        pieza.setUbicacion("ALMACEN");
        Pieza response = piezaDao.save(pieza);
        if( response!= null){
            Movimiento mov = movimientoDao.save(Movimiento.builder()
                    .fecha(new Date())
                    .cantidad(pieza.getMetraje())
                    .tipoOperacion("ANULAR DIV")
                    .usuario("")
                    .pieza(response)
                    .build());
        }

        return response;
    }
*/
    @Override
    public Pieza anular(IdRequestDto requestDto) {
        Movimiento movimiento = movimientoDao.findById(requestDto.getId()).orElse(null);
        String operacion = movimiento.getTipoOperacion();
        Pieza pieza = piezaDao.findByID(movimiento.getPieza().getId());
        Pieza response;
        if(operacion.equals("RETIRO")){

            pieza.setUbicacion("ALMACEN");
            response = piezaDao.save(pieza);
            if( response!= null){
                Movimiento mov = movimientoDao.save(Movimiento.builder()
                        .fecha(new Date())
                        .cantidad(pieza.getMetraje())
                        .tipoOperacion("ANULAR RET")
                        .usuario("")
                        .pieza(response)
                        .build());
            }

        }else {
            pieza.AgregarMetraje(movimiento.getCantidad());
            pieza.setUbicacion("ALMACEN");
            response = piezaDao.save(pieza);
            if( response!= null){
                Movimiento mov = movimientoDao.save(Movimiento.builder()
                        .fecha(new Date())
                        .cantidad(pieza.getMetraje())
                        .tipoOperacion("ANULAR DIV")
                        .usuario("")
                        .pieza(response)
                        .build());
            }

        }
        return response;
    }

}
