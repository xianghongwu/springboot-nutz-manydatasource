package com.xhw.nutzmanydatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//开启事务
public class SpringbootNutzManydatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootNutzManydatasourceApplication.class, args);
	}

}
