package com.situ.scrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
//@MapperScan  basePackages 扫描的基本包名称  annotationClass:要处理的注解
@SpringBootApplication
@MapperScan(basePackages = "com.situ.scrm", annotationClass = Repository.class)
public class BtoaApplication {
	// 此处为SpringBoot项目的启动入口
	public static void main(String[] args) {
		SpringApplication.run(BtoaApplication.class, args);
	}

}
