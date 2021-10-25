package com.example.Avatex_api.dto.inventario;

import com.example.Avatex_api.dto.common.AnioMesRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KardexRequestDto {
    private String producto;
    private AnioMesRequestDto fecha;
}
