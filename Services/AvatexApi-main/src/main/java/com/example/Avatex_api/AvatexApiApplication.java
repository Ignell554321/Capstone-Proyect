package com.example.Avatex_api;


import com.example.Avatex_api.utils.Automation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AvatexApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvatexApiApplication.class, args);
	}

	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder){
		return builder.sources(Automation.class);
	}

}
