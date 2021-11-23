package com.example.Avatex_api;


import com.example.Avatex_api.utils.Automation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class AvatexApiApplication {
/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
*/

	public static void main(String[] args) {
		SpringApplication.run(AvatexApiApplication.class, args);
	}

	protected SpringApplicationBuilder configure (SpringApplicationBuilder builder){
		return builder.sources(Automation.class);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		List<String> passwords = new ArrayList<>();
		passwords.add("admin123");

		for (String p:passwords) {
			String passwordBCrypt = passwordEncoder.encode(p);
			System.out.println(passwordBCrypt);
		}

	}
*/

}
