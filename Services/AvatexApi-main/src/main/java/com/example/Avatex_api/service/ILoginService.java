package com.example.Avatex_api.service;

import com.example.Avatex_api.dto.login.LoginRequestDto;

public interface ILoginService {

    public String autenticacion (LoginRequestDto requestDto);
}
