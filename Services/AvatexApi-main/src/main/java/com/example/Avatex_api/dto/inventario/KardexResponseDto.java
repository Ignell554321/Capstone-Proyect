package com.example.Avatex_api.dto.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KardexResponseDto {

    private String descripcion;
    private double saldoAnterior;
    private double costo;
    private double compras;
    private double ventas;
    private double saldoActual;

}
