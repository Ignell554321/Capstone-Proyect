package com.example.Avatex_api.utils;

import com.example.Avatex_api.dto.inventario.KardexResponseDto;
import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Producto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
public class Utils {


    public KardexResponseDto parseKardexResponse(Kardex kardex){

        return KardexResponseDto.builder().
                descripcion(kardex.getProducto()).
                costo(kardex.getCosto()).
                compras(kardex.getTotalCompras()).
                ventas(kardex.getTotalVentas()).
                saldoAnterior(kardex.getSaldoMesAnterior()).
                saldoActual(kardex.getSaldoMesActual()).build();

    }

    public Kardex parseKardex(Kardex kardexAnterior){
        return Kardex.builder().
                producto(kardexAnterior.getProducto()).
                mes(getMesActual()).
                anio(getAñoActual()).
                saldoMesAnterior(kardexAnterior.getSaldoMesAnterior()).
                saldoMesActual(kardexAnterior.getSaldoMesAnterior()).
                build();

    }
    public Kardex setKardexFirst(Producto producto){

        return Kardex.builder().
                producto(producto.getNombre()).
                mes(getMesActual()).
                anio(getAñoActual()).
                build();

    }

    public String getMesActual(){
        Date date = new Date();
        SimpleDateFormat month = new SimpleDateFormat("MMMM");
        log.info("MES: "+month.format(date).toUpperCase());
        return month.format(date).toUpperCase();
    }
    public String getAñoActual(){
        Date date = new Date();
        SimpleDateFormat year = new SimpleDateFormat("YYYY");
        log.info("AÑO: "+year.format(date).toUpperCase());
        return year.format(date).toUpperCase();
    }
    public String getMesAnterior(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH -1);
        log.info("MES: "+getMes(month));
        return getMes(month);
    }

    public String getMes(int mes){

        switch (mes){
            case 1: return "Enero";
            case 2: return "Febrero";
            case 3: return "Marzo";
            case 4: return "Abril";
            case 5: return "Mayo";
            case 6: return "Junio";
            case 7: return "Julio";
            case 8: return "Agosto";
            case 9: return "Setiembre";
            case 10: return "Octubre";
            case 11: return "Noviembre";
            case 12: return "Diciembre";
            default: return "";
        }
    }
}
