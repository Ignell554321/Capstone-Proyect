package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IDetalleVenta;
import com.example.Avatex_api.dao.IPiezaDao;
import com.example.Avatex_api.dao.IUsuarioDao;
import com.example.Avatex_api.dao.IVentaDao;
import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.dto.venta.DetalleVentaRequestType;
import com.example.Avatex_api.dto.venta.DetalleVentaResponseType;
import com.example.Avatex_api.dto.venta.VentaRequestDto;
import com.example.Avatex_api.dto.venta.VentaResponseDto;
import com.example.Avatex_api.entity.DetalleVenta;
import com.example.Avatex_api.entity.Usuario;
import com.example.Avatex_api.entity.Venta;
import com.example.Avatex_api.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VentaService implements IVentaService {

    @Autowired
    private IPiezaDao piezaDao;
    @Autowired
    private IVentaDao ventaDao;
    @Autowired
    private IDetalleVenta detalleventaDao;
    @Autowired
    private IUsuarioDao usuarioDao;


    @Override
    public List<Venta> findVentas() {
        return (List<Venta>) ventaDao.findAll();
    }

    @Override
    public Page<Venta> findAll(Pageable pageable) {
        return ventaDao.findByEstado("CREADO",pageable);
    }

    @Override
    public Venta findVentaByID(Long id) {
    	
    	Venta entity=ventaDao.findById(id).orElse(null);
    	return entity;
    	/*VentaResponseDto response=new VentaResponseDto();
    	VentaRequestDto requestDto=new VentaRequestDto();
    	Venta entity=ventaDao.findById(id).orElse(null);
    	
    	for (DetalleVenta detalle : entity.getDetalleVentas()) {
    		DetalleVentaRequestType detalleVenta=new DetalleVentaRequestType();
			detalleVenta.setIdProducto(detalle.getIdProducto());
			detalleVenta.setMetraje(detalle.getMetraje());
			detalleVenta.setPrecio(detalle.getPrecio());
			detalleVenta.setProducto(detalle.getProducto());
			detalleVenta.setSubtotal(detalle.getSubTotal());
			requestDto.getListaDetalles().add(detalleVenta);
		}
    	try {
    		response = setVentaResponse(entity);
			response.setDetalleVentas(guardarDetallesVenta(requestDto,entity));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return response;*/
    }

    @Override
    public Page<Venta> findByDate(BuscaXFechaRequestDto requestDto, Pageable pageable) {
        Page<Venta> ventas = ventaDao.findByFechaRegistro(
                requestDto.getYear(),requestDto.getMonth(),requestDto.getDay(), pageable);
        return ventas;
    }

    @Override
    public Page<Venta> findByMonth(AnioMesRequestDto requestDto, Pageable pageable) {
        int year = Integer.parseInt(requestDto.getAnio());
        int month = requestDto.calcularNroMes();
        Page<Venta> ventas = ventaDao.findAllByMonth(year,month,pageable);
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
                venta.setUsuario(user);
                response = setVentaResponse(ventaDao.save(venta));
                response.setDetalleVentas(guardarDetallesVenta(requestDto,venta));
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

        VentaResponseDto response = new VentaResponseDto();
        response.setId(venta.getId());
        response.setEstado(venta.getEstado());
        response.setFechaRegistro(venta.getFechaRegistro());
        response.setMontoTotal(venta.getMontoTotal());
        response.setIdUsuario(venta.getUsuario().getId());
        return response;
    }

    private List<DetalleVentaResponseType> guardarDetallesVenta (VentaRequestDto requestDto, Venta venta) throws Exception {

        List<DetalleVentaResponseType> listaResp = new ArrayList<>();
        log.info("GUARDANDO DETALLES : ");
        for(DetalleVentaRequestType detalleRequest: requestDto.getListaDetalles()){
            DetalleVenta detalleVenta = new DetalleVenta();
            try{
                detalleVenta = setDetalleVentaRequest(detalleRequest,venta);
                listaResp.add(setDetalleVentaResponse(detalleventaDao.save(detalleVenta)));
            }catch (Exception e){
                throw new Exception("Se produjo un error al guardar detalleVenta: " + e);
            }
        }
        return listaResp;
    }

    private DetalleVenta setDetalleVentaRequest (DetalleVentaRequestType detalleRequest, Venta venta){

        DetalleVenta detalleVenta = new DetalleVenta();
        detalleVenta.setPrecio(detalleRequest.getPrecio());
        detalleVenta.setMetraje(detalleRequest.getMetraje());
        detalleVenta.setProducto(detalleRequest.getProducto());
        detalleVenta.setSubTotal(detalleRequest.getSubtotal());
        detalleVenta.setIdProducto(detalleRequest.getIdProducto());
        detalleVenta.setVenta(venta);
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
