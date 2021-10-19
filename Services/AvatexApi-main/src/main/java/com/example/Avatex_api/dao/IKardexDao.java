package com.example.Avatex_api.dao;


import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Venta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface IKardexDao extends PagingAndSortingRepository<Kardex,Long> {

    //Busqueda con paginacion
    @Query(nativeQuery = true, value="select * from kardex k where k.anio=?1 and k.mes=?2")
    public List<Kardex> findByMes (String year, String month, Pageable pageable);

    @Query(nativeQuery = true, value="select * from kardex k where k.anio=?1 and k.mes=?2")
    public List<Kardex> findAllByMes (String year, String month);
}
