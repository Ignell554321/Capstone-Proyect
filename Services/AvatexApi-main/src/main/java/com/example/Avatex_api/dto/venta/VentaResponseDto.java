package com.example.Avatex_api.dto.venta;

import com.example.Avatex_api.entity.DetalleVenta;
import com.example.Avatex_api.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponseDto {

    private Long id;
    private Date fechaRegistro;
    private double montoTotal;
    private String estado;
    private Long idUsuario;
    private List<DetalleVentaResponseType> detalleVentas;
}
