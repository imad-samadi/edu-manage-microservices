package com.micro.ModuleService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Module Service Rest API Documentation",
				description = "Rest API Documentation",
				version = "V1",
				contact = @Contact(
						name = "IMAD SAMADI",
						email = "imadsamadi999@gmail.com"

				)

		)
)
public class ModuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuleServiceApplication.class, args);
	}

}
