package com.imad.springAuthServer;

import com.imad.springAuthServer.DTO.authServerInfo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Auth Server API Documentation",
				description = "Rest API Documentation for the Auth Server",
				version = "V1",
				contact = @Contact(
						name = "IMAD SAMADI",
						email = "imadsamadi999@gmail.com"

				)

		)
)
@EnableConfigurationProperties(value = {authServerInfo.class})
public class SpringAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthServerApplication.class, args);
	}

}
