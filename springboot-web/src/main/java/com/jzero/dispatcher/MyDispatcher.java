package com.jzero.dispatcher;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyDispatcher {
	private final Environment environment;
	private @Qualifier("MMQ") BlockingQueue<String> messageQueue;	

	@Autowired
	public MyDispatcher (Environment environment, @Qualifier("MMQ") BlockingQueue<String> messageQueue) {
		this.environment = environment;
		this.messageQueue = messageQueue;
	}
	
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
