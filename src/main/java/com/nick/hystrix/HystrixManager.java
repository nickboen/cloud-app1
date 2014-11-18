package com.nick.hystrix;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class HystrixManager {

	static int counter = 0;
	
	@HystrixCommand(fallbackMethod = "defaultStores")
    public Double getPrice(Integer id) {
        if(counter<2){
        	counter++;
        	return Double.valueOf(14.99);
        }
        else{
        	counter=0;
        	throw new RuntimeException("Failed primary");
        }
    }

    public Double defaultStores(Integer id) {
        return Double.valueOf(99.99);
    }
    
}
