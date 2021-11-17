
package com.example.Avatex_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServeConfig extends ResourceServerConfigurerAdapter {



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated();
    }






/*
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	
    	    
    	http.authorizeRequests().anyRequest().authenticated().and().authorizeRequests().and()
        .cors().configurationSource(configurationSource());
    
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/venta/search").permitAll()
        		.antMatchers(HttpMethod.GET, "/compra/pagina").access("hasRole('USER')")
        		.and().authorizeRequests()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
                //.anyRequest().authenticated();
    }
*/

}
