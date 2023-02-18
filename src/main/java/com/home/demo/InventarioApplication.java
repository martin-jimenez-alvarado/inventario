package com.home.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Value("${spring.application.name}") 
	String title;
			
	@Value("${springdoc.version}")
	String springdocVersion;
			
	@Bean
	  public OpenAPI springInventoryOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title(title)
	              .description("sample application")
	              .version(springdocVersion)
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")));
	  }
}
