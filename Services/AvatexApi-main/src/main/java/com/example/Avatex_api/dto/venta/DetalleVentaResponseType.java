package com.example.Avatex_api.dto.venta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponseType {

    private Long id;
    private String producto;
    private double metraje;
    private int idProducto;
    private double precio;
    private double subTotal;
}
