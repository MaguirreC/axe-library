package com.example.Axe_library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class AxeLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AxeLibraryApplication.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;


//	@Bean
//	public CommandLineRunner createPassword(){
//		return args -> {
//			System.out.println(passwordEncoder.encode("luna23"));
//			System.out.println(passwordEncoder.encode("mahomes14"));
//		};
//	}
//}
}
