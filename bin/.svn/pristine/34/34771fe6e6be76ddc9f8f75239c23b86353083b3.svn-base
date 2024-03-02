package cn.java.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * springboot启动程序，单独存在
 * 
 * @author Administrator
 *
 */

// 包扫描 扫描： controller service task
@SpringBootApplication(scanBasePackages = { "cn.java.controller", "cn.java.service.impl", "cn.java.tasks","cn.java.shiro" })
@EnableAutoConfiguration // 开启springboot默认配置
@MapperScan(basePackages = { "cn.java.mapper" }) // mybatis的mapper扫描
@EnableScheduling // 开启定时任务
@ServletComponentScan(basePackages = { "cn.java.filters" }) // 扫描过滤器所在的包
@EnableCaching // 开启缓存，redis依赖此缓存
@EnableJpaRepositories(basePackages = { "cn.java.jpa" }) // 扫描jpa包
@EntityScan(basePackages = { "cn.java.entity" }) // 扫描持久化类
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

}
