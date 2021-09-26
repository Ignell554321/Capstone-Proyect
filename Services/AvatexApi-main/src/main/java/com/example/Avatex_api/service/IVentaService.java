package com.example.Avatex_api.service;


import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import com.example.Avatex_api.dto.common.BuscaXFechaRequestDto;
import com.example.Avatex_api.dto.venta.VentaRequestDto;
import com.example.Avatex_api.dto.venta.VentaResponseDto;
import com.example.Avatex_api.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IVentaService {

    public List<Venta> findVentas();
    public Page<Venta> findAll(Pageable pageable);
    public Venta findVentaByID (Long id);
    public Page<Venta> findByDate(BuscaXFechaRequestDto requestDto, Pageable pageable);
    public Page<Venta> findByMonth(AnioMesRequestDto requestDto, Pageable pageable);
    public VentaResponseDto save(VentaRequestDto requestDto) throws Exception;
    public VentaResponseDto anular(Long id);

}
