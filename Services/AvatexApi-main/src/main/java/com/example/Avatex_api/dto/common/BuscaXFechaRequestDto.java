package com.example.Avatex_api.dto.common;

public class BuscaXFechaRequestDto {

    private String fecha;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

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
