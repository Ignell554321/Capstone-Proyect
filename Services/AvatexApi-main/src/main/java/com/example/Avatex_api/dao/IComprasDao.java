package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
public interface IComprasDao extends PagingAndSortingRepository<Compra,Long> {
  

	@Query(nativeQuery = true, value="select * from compras where fecha_pago=?1 and estado=?2")
	public Page<Compra> findByFechaPagoAndEstado(String fechaPago, String estado,Pageable pageable);
	
	public Page<Compra> findByEstado(String estado,Pageable pageable);

	@Query(nativeQuery = true, value="select * from compras c where year(c.fecha_pago)=?1 and month(c.fecha_pago)=?2")
	public Page<Compra> findAllByMonth(Integer year, Integer month,Pageable pageable);

	public Page<Compra> findByProveedor(String proveedor, Pageable pageable);
}
