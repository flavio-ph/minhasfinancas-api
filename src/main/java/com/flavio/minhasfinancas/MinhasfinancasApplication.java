package com.flavio.minhasfinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinhasfinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhasfinancasApplication.class, args);
		HellowWorld();
	}
	public static void HellowWorld() {
		System.out.println("Hellow, world!");
	}

}
