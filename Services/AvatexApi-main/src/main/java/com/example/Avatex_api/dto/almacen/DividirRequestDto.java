package com.example.Avatex_api.dto.almacen;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DividirRequestDto {

    private Long id;
    private Double metraje;
}
