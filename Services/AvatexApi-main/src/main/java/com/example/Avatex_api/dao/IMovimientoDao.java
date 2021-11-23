package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Movimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento,Long> {

    public Page<Movimiento> findAll(Pageable pageable);
}
