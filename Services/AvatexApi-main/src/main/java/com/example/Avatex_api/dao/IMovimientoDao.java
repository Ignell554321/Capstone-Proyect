package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Movimiento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IMovimientoDao extends PagingAndSortingRepository<Movimiento,Long> {
}
