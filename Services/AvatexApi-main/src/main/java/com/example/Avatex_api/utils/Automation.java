package com.example.Avatex_api.utils;

import com.example.Avatex_api.entity.Kardex;
import com.example.Avatex_api.entity.Producto;
import com.example.Avatex_api.service.IInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Automation {

    @Autowired
    private IInventarioService inventarioService;

    @Autowired
    private Utils utils;

    @Scheduled(cron="0 0 0 1 1-12 ?")
    private void generarKardexDelMes (){

        List<Kardex> kardexList = new ArrayList<>();
        for (Kardex k:kardexList ) {
            utils.parseKardex(k);
        }
    }

}
