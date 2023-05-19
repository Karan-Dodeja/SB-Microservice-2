package com.serviceregistrey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceregistreyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceregistreyApplication.class, args);
	}

}
depa