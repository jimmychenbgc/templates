package com.jzero.processor;

import java.util.concurrent.BlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MyProcessor {

	private final Environment environment;
	private final BlockingQueue<String> messageQueue;
	
	@Autowired
	public MyProcessor(Environment environment, @Qualifier("MMQ") BlockingQueue<String> messageQueue) {
		this.environment = environment;
		this.messageQueue = messageQueue;
	}
	
	@Async
	public void startProcess() {
		while (true) {
			try {
				System.out.println(messageQueue.take());
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
