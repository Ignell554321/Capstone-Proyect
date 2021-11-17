package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IPiezaDao extends PagingAndSortingRepository<Pieza,Long> {

    @Query(value = "SELECT * FROM piezas p WHERE p.id = :id", nativeQuery = true)
    Pieza findByID(@Param("id") Long id);

    @Query(value = "SELECT * FROM piezas p WHERE p.producto_id = ?1 and p.ubicacion='ALMACEN'", nativeQuery = true)
    public Page<Pieza> findByProducto(Long idProducto,Pageable pageable);

}
