package com.example.Avatex_api.controller;

import com.example.Avatex_api.dto.login.LoginRequestDto;
import com.example.Avatex_api.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class LoginRestController {

    @Autowired
    private ILoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> obtenerPiezas(@RequestBody LoginRequestDto requestDto){
        return ResponseEntity.ok().body(loginService.autenticacion(requestDto));
    }
}
