package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Compra;
import com.example.Avatex_api.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IVentaDao extends PagingAndSortingRepository<Venta, Long>{

	@Query(nativeQuery = true, value="select * from ventas v where year(v.fecha_registro)=?1 and month(v.fecha_registro)=?2 and day(v.fecha_registro)=?3")
	public Page<Venta> findByFechaRegistro (Integer year, Integer month, Integer day, Pageable pageable);

	@Query(nativeQuery = true, value="select * from ventas v where year(v.fecha_registro)=?1 and month(v.fecha_registro)=?2")
	public Page<Venta> findAllByMonth(Integer year, Integer month, Pageable pageable);

	@Query(nativeQuery = true, value="select * from ventas v where v.estado=?1")
	public Page<Venta> findByEstado(String estado,Pageable pageable);
	
}
