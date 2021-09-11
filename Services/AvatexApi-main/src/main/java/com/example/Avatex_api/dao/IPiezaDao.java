package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Pieza;
import com.example.Avatex_api.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IPiezaDao extends JpaRepository<Pieza,Long> {

    @Query(value = "SELECT * FROM piezas p WHERE p.id = :id", nativeQuery = true)
    Pieza findByID(@Param("id") Long id);
    @Query(value = "SELECT p.id, p.metraje, p.color, p.ubicacion, p.fecha, p.producto_id " +
            "FROM piezas p INNER JOIN productos pd ON (p.producto_id = pd.id)  WHERE pd.nombre = :nombre",
            nativeQuery = true)
    List<Pieza> findByName(@Param("nombre") String nombre);
}
