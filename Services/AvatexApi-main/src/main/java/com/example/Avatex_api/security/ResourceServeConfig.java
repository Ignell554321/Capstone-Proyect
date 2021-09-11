/*
package com.example.Avatex_api.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/productos").permitAll()
                .antMatchers(HttpMethod.GET, "/api/productos/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/productos/piezas/{nombre}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/productos").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/productos/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/movimientos/fecha/{fecha}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/movimientos/nombre/{nombre}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/movimientos/pedido").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/api/movimientos/retiro").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/movimientos/retiro/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/movimientos/cancel/{id}").hasRole("USER")
                .anyRequest().authenticated();
    }


}
*/