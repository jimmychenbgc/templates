package com.jzero.service;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.jzero.dispatcher.MyDispatcher;
import com.jzero.processor.MyProcessor;

@Configuration
@Service
public class MyService {
	private Logger logger = LoggerFactory.getLogger(MyService.class);
	private final MyDispatcher myDispatcher;
	private final MyProcessor myProcessor;
	
	@Value("${my.property}")
	private String myProperty;
	
	@Autowired
	public MyService (MyProcessor myProcessor, MyDispatcher myDispatcher) {
		this.myProcessor = myProcessor;
		this.myDispatcher = myDispatcher;		
	}
	
	@PostConstruct
	public void startAllAsyncServices() {
		logger.info("my.property" + myProperty);
		myDispatcher.startDispatcher();
		myProcessor.startProcess();
	}		
}
