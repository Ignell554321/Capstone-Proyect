package com.example.Avatex_api.dao;

import com.example.Avatex_api.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao  extends JpaRepository<Usuario, Long> {

    @Query(nativeQuery = true, value="select id from usuarios where username=?1")
    public Long findIdUser(String username);
    public Usuario findByUsername(String username);
}