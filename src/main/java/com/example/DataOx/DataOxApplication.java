package com.example.DataOx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.DataOx.repository")
@EnableAutoConfiguration
public class DataOxApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataOxApplication.class, args);
    }

}
