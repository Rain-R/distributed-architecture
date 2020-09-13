package com.framewor.example.srpingcloudconfiguserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SrpingCloudConfigUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SrpingCloudConfigUserServiceApplication.class, args);
	}

}
