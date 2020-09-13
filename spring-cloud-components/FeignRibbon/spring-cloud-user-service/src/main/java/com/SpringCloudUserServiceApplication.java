package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.framework.example.client")
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudUserServiceApplication.class, args);
	}

}
