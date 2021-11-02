package com.example.Avatex_api.dto.almacen;

import com.example.Avatex_api.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PiezaRequestDto {

    private Long id;
    private Producto producto;
    private Double metraje;

}
