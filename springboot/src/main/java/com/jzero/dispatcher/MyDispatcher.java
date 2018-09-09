package com.jzero.dispatcher;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyDispatcher {
	private final Logger logger = LoggerFactory.getLogger(MyDispatcher.class);
	private final Environment environment;
	private @Qualifier("MMQ") BlockingQueue<String> messageQueue;	

	@Autowired
	public MyDispatcher (Environment environment, @Qualifier("MMQ") BlockingQueue<String> messageQueue) {
		this.environment = environment;
		this.messageQueue = messageQueue;
	}
	
	@Profile("dev")
	@Async
	public void startDispatcher() {
		while(true) {
			try {
				
				messageQueue.put("message: " + new Date().getTime());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}	
}
