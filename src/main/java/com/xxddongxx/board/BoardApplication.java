package com.xxddongxx.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.swing.*;

@EnableJpaAuditing
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {

		SpringApplication.run(BoardApplication.class, args);
	}

	@Bean(name = "uploadPath")
	public String uploadPath() {
		return "C:\\workspace\\java\\Spring-Boot-Baord\\src\\main\\resources\\static\\image";
	}
}
