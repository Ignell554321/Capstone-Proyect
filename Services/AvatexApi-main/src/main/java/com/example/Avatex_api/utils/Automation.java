package com.example.Avatex_api.utils;

import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IInventarioService;
import com.example.Avatex_api.service.impl.VentaService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class Automation {

    @Autowired
    private IInventarioService inventarioService;

    @Autowired
    private Utils utils;

    @Scheduled(cron="0 55 21 25 1-12 ?")
    private void generarKardexDelMes (){

    	log.info("mensaje de prueba");
    	
        List<Kardex> kardexList = new ArrayList<>();
        for (Kardex k:kardexList ) {
            utils.parseKardex(k);
        }
    }

}
