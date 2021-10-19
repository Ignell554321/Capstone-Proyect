package com.example.Avatex_api.dto.inventario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventarioResponseDto {

    private String mes;
    private List<KardexResponseDto> listaKardex;
}
