package com.example.Avatex_api.service;


import com.example.Avatex_api.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface IVentaService {

    public List<Venta> findVentas();
    public Page<Venta> findAll(Pageable pageable);
    public Venta findVentaByID (Long id);
    public Venta save(Venta venta);
    public Venta update(Venta venta);
    public Venta delete(Long id);

}
