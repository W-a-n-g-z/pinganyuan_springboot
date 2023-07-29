package com.enss.ipfsgate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAsync
@MapperScan("com.enss.ipfsgate.mapper")
public class IpfsGateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpfsGateApplication.class, args);
	}

}
