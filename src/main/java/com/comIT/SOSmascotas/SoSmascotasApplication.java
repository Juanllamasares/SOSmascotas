package com.comIT.SOSmascotas;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Configuration  	
@Import({ SecurityConfig.class })
@SpringBootApplication
public class SoSmascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoSmascotasApplication.class, args);
		
	}
	
}


