package com.example.Avatex_api.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BuscaXFechaRequestDto {

    private String fecha;

    public Integer getYear(){
        return Integer.parseInt(fecha.substring(0,4));
    }
    public Integer getMonth(){
        return Integer.parseInt(fecha.substring(5,7));
    }
    public Integer getDay(){
        return Integer.parseInt(fecha.substring(8,10));
    }
}
