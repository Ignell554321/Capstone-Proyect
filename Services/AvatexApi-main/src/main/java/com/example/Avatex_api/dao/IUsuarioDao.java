package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao  extends JpaRepository<Usuario, Long> {
    public Usuario findByUsername(String username);
}