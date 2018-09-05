package com.jzero.service;

import java.util.concurrent.BlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.jzero.processor.MyProcessor;

@Service
public class MyService {
	private final MyProcessor myProcessor;
	
	private final BlockingQueue<String> messageQueue;
	
	
	@Autowired
	public MyService (MyProcessor myProcessor, @Qualifier("MMQ") BlockingQueue<String> messageQueue) {
		this.myProcessor = myProcessor;
		this.messageQueue = messageQueue;
	}
	
	@PostConstruct
	public void startAllAsyncServices() {
		
	}		
}
