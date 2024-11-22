package com.example.dockerdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableJpaRepositories(basePackages = "com.example.dockerdemo.repository")
@EntityScan(basePackages = "com.example.dockerdemo.entity")
@EnableCaching
@CrossOrigin
public class DockerAdvanceAssignment {

	public static void main(String[] args) {

		System.out.println("===========App Start===========");
		SpringApplication.run(DockerAdvanceAssignment.class, args);

	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		System.out.println("===========Docker Start===========");
		return String.format("Hello %s!", name);
	}


}
