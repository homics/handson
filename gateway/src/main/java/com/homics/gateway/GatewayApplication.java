package com.homics.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// TODO 2:
//  You need to add an annotation to enable Zuul. Check the documentation:
//  https://cloud.spring.io/spring-cloud-netflix/multi/multi__router_and_filter_zuul.html
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
