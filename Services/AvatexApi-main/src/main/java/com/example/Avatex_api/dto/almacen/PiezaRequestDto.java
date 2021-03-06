package com.example.Avatex_api.dto.almacen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PiezaRequestDto {

    private Long idProducto;
    private Double metraje;
    private String color;

}
