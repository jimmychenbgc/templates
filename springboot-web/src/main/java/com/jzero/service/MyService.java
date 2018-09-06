package com.jzero.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzero.dispatcher.MyDispatcher;
import com.jzero.processor.MyProcessor;

@Service
public class MyService {
	private final MyDispatcher myDispatcher;
	private final MyProcessor myProcessor;
	
	@Autowired
	public MyService (MyProcessor myProcessor, MyDispatcher myDispatcher) {
		this.myProcessor = myProcessor;
		this.myDispatcher = myDispatcher;		
	}
	
	@PostConstruct
	public void startAllAsyncServices() {
		myDispatcher.startDispatcher();
		myProcessor.startProcess();
	}		
}
