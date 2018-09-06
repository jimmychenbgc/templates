package com.jzero.processor;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyProcessor {
	private final Logger logger = LoggerFactory.getLogger(MyProcessor.class);
	
	private final Environment environment;
	private final BlockingQueue<String> messageQueue;
	
	@Autowired
	public MyProcessor(Environment environment, @Qualifier("MMQ") BlockingQueue<String> messageQueue) {
		this.environment = environment;
		this.messageQueue = messageQueue;
		for (String activeProfile : environment.getActiveProfiles()) {
			logger.info(activeProfile);
		}
	}
	
	@Async
	public void startProcess() {
		while (true) {
			try {
				logger.info(messageQueue.take());				
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
