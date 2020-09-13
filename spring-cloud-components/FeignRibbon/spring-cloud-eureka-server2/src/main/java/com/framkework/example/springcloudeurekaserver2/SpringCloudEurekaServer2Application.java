package com.framkework.example.springcloudeurekaserver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SpringCloudEurekaServer2Application {

	public static void main(String[] args) {
		System.out.println("66666666666666");
		SpringApplication.run(SpringCloudEurekaServer2Application.class, args);
	}

}
