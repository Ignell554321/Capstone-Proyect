package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface IVentaDao extends PagingAndSortingRepository<Venta, Long>{

	
	
}
