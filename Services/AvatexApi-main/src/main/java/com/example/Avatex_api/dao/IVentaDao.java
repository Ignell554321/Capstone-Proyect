package com.example.Avatex_api.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.Venta;

public interface IVentaDao extends JpaRepository<Venta, Long>{

	@Query(nativeQuery = true, value="select * from venta where fecha_venta=?1")
	public Page<Compra> findByFechaPago(String fechaVenta,Pageable pageable);
	
	
}
