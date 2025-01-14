package com.micro.EnrollmentService;

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
				title = "INSCRIPTION Service Rest API Documentation",
				description = "Enroll students in modules.\n" +
						"Cancel enrollments.\n" +
						"Retrieve enrollments for a student.\n" +
						"Retrieve enrollments for a module.",
				version = "V1",
				contact = @Contact(
						name = "IMAD SAMADI",
						email = "imadsamadi999@gmail.com"

				)

		)
)
public class EnrollmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentServiceApplication.class, args);
	}

}
