package com.example.Avatex_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class AvatexApiApplication implements CommandLineRunner {
/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
*/
	public static void main(String[] args) {
		SpringApplication.run(AvatexApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<String> passwords = new ArrayList<>();
		passwords.add("vhugo55");
		passwords.add("angela59");
		passwords.add("cristhian78");
		passwords.add("hugo84");
/*
		for (String p:passwords) {
			String passwordBCrypt = passwordEncoder.encode(p);
			System.out.println(passwordBCrypt);
		}

 */
	}
}
