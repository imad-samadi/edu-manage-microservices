package com.micro.studentService;

import com.micro.studentService.contactApi.studentContactInfo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication

@EnableConfigurationProperties(value = {studentContactInfo.class})
@EnableFeignClients
@OpenAPIDefinition(
		info = @Info(
				title = "Student Service Rest API Documentation",
				description = "Rest API Documentation",
				version = "V1",
				contact = @Contact(
						name = "IMAD SAMADI",
						email = "imadsamadi999@gmail.com"

				)

		)
)
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}
