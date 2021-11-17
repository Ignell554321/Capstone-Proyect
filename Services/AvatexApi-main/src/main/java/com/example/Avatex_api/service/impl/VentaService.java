package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IKardexDao;
import com.example.Avatex_api.dao.IUsuarioDao;
import com.example.Avatex_api.dao.IVentaDao;
import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.dto.venta.DetalleVentaRequestType;
import com.example.Avatex_api.dto.venta.DetalleVentaResponseType;
import com.example.Avatex_api.dto.venta.VentaRequestDto;
import com.example.Avatex_api.dto.venta.VentaResponseDto;
import com.example.Avatex_api.entity.DetalleVenta;
import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Usuario;
import com.example.Avatex_api.entity.Venta;
import com.example.Avatex_api.service.IVentaService;
import com.example.Avatex_api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VentaService implements IVentaService {


    @Autowired
    private IVentaDao ventaDao;
    @Autowired
    private IUsuarioDao usuarioDao;
    @Autowired
    private IKardexDao kardexDao;
    @Autowired
    private Utils utils;


    @Override
    public List<Venta> findVentas() {
        return (List<Venta>) ventaDao.findAll();
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return ventaDao.findByEstado("CREADO",pageable);
    }

    @Override
    public VentaResponseDto findVentaByID(Long id) {

        VentaResponseDto response=new VentaResponseDto();
    	Venta venta=ventaDao.findById(id).orElse(null);
        if(venta != null){
            response = setVentaResponse(venta);
        }
    	return response;
    }

    @Override
    public Page<Venta> findByDate(BuscaXFechaRequestDto requestDto, Pageable pageable) {
        Page<Venta> ventas = ventaDao.findByFechaRegistro(
                requestDto.getYear(),requestDto.getMonth(),requestDto.getDay(), pageable);
        return ventas;
    }

    @Override
    public Page<Venta> findByMonth(AnioMesRequestDto requestDto, Pageable pageable) {

        Page<Venta> ventas = ventaDao.findAllByMonth(requestDto.parseAñoInt(), requestDto.getNroMes(), pageable);
        return ventas;
    }


    @Override
    public VentaResponseDto save(VentaRequestDto requestDto) throws Exception {
        Venta venta = new Venta();
        VentaResponseDto response;
        if(requestDto.getListaDetalles().size()>0 && requestDto.getListaDetalles() != null){
            try{
                venta.setCurrentDate();
                venta.setMontoTotal(requestDto.getMontoTotal());
                venta.setEstado("CREADO");
                Usuario user = usuarioDao.findByUsername(requestDto.getUsername());
                venta.setUsuario(requestDto.getUsername());

                //NUEVO CODIGO
                List<DetalleVenta> lista = new ArrayList<>();
                log.info("GUARDANDO DETALLES : ");
                for(DetalleVentaRequestType detalleRequest: requestDto.getListaDetalles()){
                    DetalleVenta det = setDetalleVentaRequest(detalleRequest);
                    det.calcularSubtotal();
                    lista.add(det);
                }
                venta.setDetalleVentas(lista);
                response = setVentaResponse(ventaDao.save(venta));
                
                try{
                    for (DetalleVentaResponseType dv:response.getDetalleVentas()) {
                        Kardex kardex = kardexDao.findByMesProducto(utils.getAñoActual(), utils.getMesActual(), dv.getProducto());
                        kardex.disminuirSaldo(dv.getMetraje());
                        kardex.aumentarTotalVentas(dv.getMetraje());
                        kardexDao.save(kardex);
                    }
                }catch (Exception error){
                    throw new Exception("Se produjo un error al actualizar el kardex: " + error);
                }
            }catch (Exception er){
                throw new Exception("Se produjo un error al guardar la venta: " + er);
            }
            //FALTA DESCONTAR EN EL INVENTARIO

        }else {
            throw new Exception("Los datos ingresados no son correctos: Request { "+requestDto+" }");
        }
        return response;
    }

    @Override
    public VentaResponseDto anular(Long id) {

        Venta venta = ventaDao.findById(id).orElse(null);
        venta.setEstado("ANULADA");
        VentaResponseDto response = setVentaResponse(ventaDao.save(venta));
        return response;
    }

    //METODOS PRIVADOS


    private VentaResponseDto setVentaResponse (Venta venta) {

        log.info("INICIO DE ACTIVIDAD: setVentaResponse ");
        log.info("Venta nro detalles: "+venta.getDetalleVentas().size() );
        VentaResponseDto response = new VentaResponseDto();
        List<DetalleVentaResponseType> lista = new ArrayList<>();
        response.setId(venta.getId());
        response.setEstado(venta.getEstado());
        response.setFechaRegistro(venta.getFechaRegistro());
        response.setMontoTotal(venta.getMontoTotal());
        response.setUsuario(venta.getUsuario());

        for(DetalleVenta det: venta.getDetalleVentas()){
            DetalleVentaResponseType detResponse = setDetalleVentaResponse(det);
            lista.add(detResponse);
        }
        response.setDetalleVentas(lista);
        return response;
    }

    private DetalleVenta setDetalleVentaRequest (DetalleVentaRequestType detalleRequest){

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setPrecio(detalleRequest.getPrecio());
        detalleVenta.setMetraje(detalleRequest.getMetraje());
        detalleVenta.setProducto(detalleRequest.getProducto());
        detalleVenta.setSubTotal(detalleRequest.getSubtotal());
        detalleVenta.setIdProducto(detalleRequest.getIdProducto());
        return detalleVenta;
    }

    private DetalleVentaResponseType setDetalleVentaResponse (DetalleVenta det){

        DetalleVentaResponseType response = new DetalleVentaResponseType();
        response.setId(det.getId());
        response.setMetraje(det.getMetraje());
        response.setProducto(det.getProducto());
        response.setSubTotal(det.getSubTotal());
        response.setPrecio(det.getPrecio());
        response.setIdProducto(det.getIdProducto());
        return response;
    }




}
