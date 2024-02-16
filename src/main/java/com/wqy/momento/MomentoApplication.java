package com.wqy.momento;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@MapperScan("com.wqy.momento.mapper")
@SpringBootApplication
public class MomentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentoApplication.class, args);
	}

}
