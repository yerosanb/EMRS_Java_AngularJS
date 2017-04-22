package org.oib.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("org.oib.repository")
@ComponentScan("org.oib")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
