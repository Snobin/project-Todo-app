package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@SpringBootApplication
public class ProjectManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementApplication.class, args);
	}
	
	@Bean
	public OpenAPI openApi() {
		Info info = new Info().description("Project-Todo app with Springboot and Angular").title("Take Home Challenge")
				.version("V1");
		return new OpenAPI().info(info);
	}

}
