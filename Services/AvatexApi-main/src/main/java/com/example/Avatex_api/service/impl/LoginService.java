package com.example.Avatex_api.service.impl;

import com.example.Avatex_api.dao.IUsuarioDao;
import com.example.Avatex_api.dto.login.LoginRequestDto;
import com.example.Avatex_api.entity.Usuario;
import com.example.Avatex_api.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    public String autenticacion(LoginRequestDto requestDto) {

        if(usuarioDao.findByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword()) != null){
            return "OK";
        }else{
            return "Usuario o contraseña no validos";
        }
    }


}
