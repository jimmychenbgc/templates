package com.jzero;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.EnableIntegrationManagement;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableIntegration
@EnableIntegrationManagement
@EnableCaching
@IntegrationComponentScan
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
	
	private BlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(10000);
	
	@Bean(name = "MMQ")
	public BlockingQueue<String> getMemoryMessageQueue() {
		return messageQueue;
	}
	
}
