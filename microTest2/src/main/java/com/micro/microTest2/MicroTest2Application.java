package com.micro.microTest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties(value = {microTest2ContactInfo.class})
@EnableFeignClients
public class MicroTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(MicroTest2Application.class, args);
	}

}
