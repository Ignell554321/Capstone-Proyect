package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IComprasDao extends JpaRepository<Compra,Long> {
    /*
    @Query(value = "SELECT * FROM compras c WHERE c.fechaPago = :fechaPago", nativeQuery = true)
    List<Compra> findByFechaPago(@Param("fechaPago") Date fechaPago);

     */
    /*
    List<Compra> findByFechaPago(Date fechaPago);
    List<Compra> findByMes(String mes);
    List<Compra> findByProveedor(String proveedor);
*/
}
