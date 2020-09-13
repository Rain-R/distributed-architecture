package com.framework.samples.dispatchapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.framework.samples.dispatchapp.mapper")
public class DispatchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(DispatchAppApplication.class, args);
	}

}
