package com.jzero.controller;

import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jzero.processor.MyProcessor;

@RestController
public class MyController {
	
	private final Logger logger = LoggerFactory.getLogger(MyController.class);
	
	private MyProcessor myProcessor; 
	
	@Autowired
	@Qualifier("MMQ")
	private BlockingQueue<String> messageQueue;
	
    @Autowired
    public MyController(MyProcessor myProcessor) {
        this.myProcessor = myProcessor;        
    }
	
    @GetMapping("/mapper/{isin}")
    public String getMapper(@PathVariable("isin") String isin)  {
        return isin;
    }

    @PostMapping("/mapper/generateFile")
    public String generateFile()  {
    	myProcessor.startProcess();
        return "File generated!!";
    }

}
